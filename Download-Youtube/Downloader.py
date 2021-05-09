from tkinter import *
from tkinter import filedialog
import os
from pytube import YouTube
from pytube import Playlist


frame = Tk()
frame.title('Download video YouTube')
frame.geometry('700x550')
titleMessage = Label(text='Youtube Video Downloader', fg='red', font=('arial', 20))
titleMessage.place(x=200, y=20)

linkM = Label(text='Choose the type of link ', font=('arial', 14))
linkM.place(x=50, y=100)

choice = "video"

def downVideo() :
    global choice
    choice='video'
def downPlayList() :
    global choice
    choice='PlayList'


v = IntVar() #command A procedure to be called every time the user changes the state of this radiobutton.
v.set(0)
Radiobutton(frame, text='Vidoe', variable=v, value=1, command=lambda : downVideo(), indicatoron=0, width=10).place(x=300, y=100)    #You could also use Python lambda operator or function to create a temporary,
Radiobutton(frame, text='Playlist', variable=v, value=2, command=lambda : downPlayList(), indicatoron=0, width=10).place(x=450, y=100)    # one-time simple function to be called when the Button is clicked


linkL = Label(frame, text='put the link ',  font=('arial', 14))
linkL.place(x=50, y=150)

vLink = StringVar(frame)
link = Entry(frame, width=40, fg='blue', font=('arial', 14), textvariable=vLink)
link.place(x=200, y=150)

QM = Label(frame, text='Choose the type of Quality ', font=('arial', 14))
QM.place(x=50, y=200)


clicked = StringVar(frame)
clicked.set('Quality')
menu = OptionMenu(frame, clicked, 'High Quality',  'Low Quality',  'audio file')
menu.place(x=300, y=200)

# pos = 350
def finish(title):
    # global pos
    list.insert(END, f'download done....{title}')
    # done = Label(frame, text=f'download done....{title}', font=('arial', 14), fg='#fc5e03')
    # done.place(x=50, y=pos)
    # pos += 5
    print('download done....')

def convert():
    filename = filedialog.askdirectory()
    filename = os.chdir(filename)

    if choice == 'video':
        video_youtube = YouTube(link.get())
    else:
        playlist_youtube = Playlist(link.get())


    if filename == '':
        Label(frame, text='please enter valid location to save').place(x=300, y=300)
    else:
        if clicked.get() == 'High Quality':
            if choice == 'video':
                video_youtube.streams.get_highest_resolution().download(output_path="")
                video_youtube.register_on_complete_callback(finish(video_youtube.title))
            else:
                for indx in playlist_youtube:
                    i = YouTube(indx)
                    i.streams.get_highest_resolution().download(output_path="")
                    i.register_on_complete_callback(finish(i.title))

        elif clicked.get() == 'Low Quality':
            if choice == 'video':

                video_youtube.streams.get_lowest_resolution().download(output_path="")
                video_youtube.register_on_complete_callback(finish(video_youtube.title))
            else:
                for indx in playlist_youtube:
                    i = YouTube(indx)
                    i.streams.get_lowest_resolution().download(output_path="")
                    i.register_on_complete_callback(finish(i.title))
        # 'audio file'
        else:
            if choice == 'video':

                video_youtube.streams.get_audio_only().download(output_path="")
                video_youtube.register_on_complete_callback(finish(video_youtube.title))
            else:
                for indx in playlist_youtube:
                    i = YouTube(indx)
                    i.streams.get_audio_only().download(output_path="")
                    i.register_on_complete_callback(finish(i.title))


# videos are downloaded
list = Listbox(frame, width=60, height=7, bg='#edcfc5',  font=('arial', 14), fg='#fc5e03')
list.place(x=20, y=350)

# button convert
btnConvert = Button(frame, text='Download', width=20, height=2, bg='red', fg='white', command=convert)
btnConvert.place(x=300, y=300)



frame.mainloop()