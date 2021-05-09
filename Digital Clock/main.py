from tkinter import *
import time

window = Tk()
window.geometry('400x200')
window.config(background='powder blue')
window.title('Digital Clock')


def Clock():

    clock_time = time.localtime()
    clock_time = time.strftime('%H:%M:%S', clock_time)
    clock_date = time.localtime()
    clock_date = time.strftime('%d/%m/%Y', clock_date)
    myLable.config(text=clock_time)
    myLable2.config(text=clock_date)
    myLable.after(1000, Clock)


myTime = Label(window, font=('arial', 20, 'bold'), fg='black', text="Time :")
myTime.place(x=50, y=50)
myLable = Label(window, font=('arial', 20, 'bold'), bg='white', fg='black')
myLable.place(x=150, y=50)

myTime2 = Label(window, font=('arial', 20, 'bold'), fg='black', text="Date :")
myTime2.place(x=50, y=100)
myLable2 = Label(window, font=('arial', 20, 'bold'), bg='white', fg='black')
myLable2.place(x=150, y=100)
Clock()

window.mainloop()
