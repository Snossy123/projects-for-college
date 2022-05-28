import pygame
import tkinter as tkr
from tkinter.filedialog import askdirectory
import os
from PIL import ImageTk, Image
from mutagen.mp3 import MP3
import time

# window
music_player = tkr.Tk()
music_player.geometry('700x450')
music_player.config(background='#173c1b')
music_player.title('Play It')
project_directory = os.getcwd()

# direction of playlist
directory = askdirectory()
os.chdir(directory)
list_songs = os.listdir()

# create a box list
playlist = tkr.Listbox(music_player, width=450, height=12, bg='#3b4119',  font=('arial', 12, 'bold'))
for i in list_songs:
    playlist.insert('end', i)

# initialize pygame , mixer to manipulating music
pygame.init()
pygame.mixer.init()

# test img
open_img2 = Image.open(f'{project_directory}\\pause.png')
resized2 = open_img2.resize((50, 50), Image.ANTIALIAS)
play_img2 = ImageTk.PhotoImage(resized2)

# current song
current_song = 0
# time_song
length_song = 1
def time_song():
    global length_song
    name = playlist.get(current_song)
    place = f'{directory}\\{name}'
    len_song = MP3(place)
    length_song = len_song.info.length
    time_style = time.strftime('%M:%S', time.gmtime(length_song))
    time_length = tkr.StringVar()
    time_length.set(time_style)
    tkr.Label(music_player, width=10, bg='#173c1b', fg="black",
                           font=('arial', 10, 'bold'),
                           textvariable=time_length).place(x=580, y=320)


# song_waste
song_waste = tkr.Label(music_player, font=('arial', 10, 'bold'), width=10, bg='#173c1b', fg="black")
current_time = 0

def time_waste():
    global current_time
    current_time = pygame.mixer.music.get_pos()/1000
    current_style = time.strftime('%M:%S', time.gmtime(current_time))
    # song current time
    song_waste.config(text=current_style)

    # if song_slider.get() == current_time/length_song * 100:
        # slider no change position
    song_slider.set(current_time/length_song * 100)
    # else:
    #     pygame.mixer.music.set_pos(int(song_slider.get() * length_song * 10))
    song_slider.after(500, time_waste)
    # song_waste.after(1000, time_waste)


# change_sound
# sound control
sound_slider = tkr.Scale(music_player, from_=0, to=100, orient='horizontal', troughcolor='#5ab862', bg='#173c1b', bd=0)
def change_sound():
    pygame.mixer.music.set_volume(sound_slider.get()/100)
    sound_slider.after(1000, change_sound)


sound_slider.config(command=change_sound())

# position control
song_slider = tkr.Scale(music_player, from_=0, to=100, length=640, orient='horizontal', showvalue=0, troughcolor='#5ab862', bg='#173c1b')
song_slider.set(int(current_time/length_song * 100))
def change_position():
    # pygame.mixer.music.(song_slider.get()/100)
    song_slider.after(1000, change_sound)


song_slider.config(command=change_position())


# mouse

# function play button
def Play(event):
    sound_slider.set(100)
    global Play_button
    global current_song
    Play_button.config(image=play_img2)
    pygame.mixer.music.load(playlist.get(tkr.ACTIVE))
    var.set(playlist.get(tkr.ACTIVE))
    pygame.mixer.music.play()
    Play_button.config(command=Pause)
    time_waste()

# playlist event
playlist.bind("<Button-1>", Play)

# function play button
def UnPause():
    global Play_button
    global play_img2
    Play_button.config(image=play_img2)
    # pygame.mixer.music.load(playlist.get(tkr.ACTIVE))
    pygame.mixer.music.unpause()
    Play_button.config(command=Pause)

# function play button
def Pause():
    global Play_button
    global play_img
    Play_button.config(image=play_img)
    # pygame.mixer.music.load(playlist.get(tkr.ACTIVE))
    pygame.mixer.music.pause()
    Play_button.config(command=UnPause)


# function play button
def Play():
    sound_slider.set(100)
    global Play_button
    Play_button.config(image=play_img2)
    pygame.mixer.music.load(playlist.get(current_song))
    var.set(playlist.get(current_song))
    pygame.mixer.music.play()
    time_song()
    Play_button.config(command=Pause)
    time_waste()




# play button
open_img = Image.open(f'{project_directory}\\play3.png')
resized = open_img.resize((50, 50), Image.ANTIALIAS)
play_img = ImageTk.PhotoImage(resized)
Play_button = tkr.Button(image=play_img, bg='#173c1b', activebackground='#173c1b', width=50, height=50, relief='flat',
                         command=Play)

# song name
var = tkr.StringVar()
song_name = tkr.Label(music_player, width=80, bg='white', fg="black",  font=('arial', 10, 'bold'),
                      textvariable=var)











# the next song
def next_song():
    global current_song
    if current_song == len(list_songs)-1:
        current_song = 0
    else:
        current_song += 1
    Play()

open_img5 = Image.open(f'{project_directory}\\next.png')
resized5 = open_img5.resize((50, 50), Image.ANTIALIAS)
play_img5 = ImageTk.PhotoImage(resized5)
nextsong = tkr.Button(image=play_img5, bg='#173c1b', activebackground='#173c1b', width=50, height=50, relief='flat',
                         command=next_song)

# the next song
def prev_song():
    global current_song
    if current_song == 0:
        current_song = len(list_songs)-1
    else:
        current_song -= 1
    Play()

open_img6 = Image.open(f'{project_directory}\\previous.png')
resized6 = open_img6.resize((50, 50), Image.ANTIALIAS)
play_img6 = ImageTk.PhotoImage(resized6)
prevsong = tkr.Button(image=play_img6, bg='#173c1b', activebackground='#173c1b', width=50, height=50, relief='flat',
                         command=prev_song)












# add elements to window
playlist.pack()
Play_button.place(x=310, y=350)
song_name.place(x=20, y=230)
sound_slider.place(x=580, y=380)
nextsong.place(x=380, y=350)
prevsong.place(x=240, y=350)
song_waste.place(x=20, y=320)
song_slider.place(x=20, y=280)




music_player.mainloop()