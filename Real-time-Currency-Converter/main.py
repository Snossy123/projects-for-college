from tkinter import *
from tkinter import messagebox
from forex_python.converter import CurrencyRates
from forex_python.converter import CurrencyCodes

# creat new frame
currencyApp = Tk()
# change title & size of frame
currencyApp.title('Currency Convertor App')
currencyApp.geometry('500x400')
# add text title
titlePage = Label(currencyApp, text='Convertor Currencies with easy way', height=2, font=('Arial', 20), fg='red')
# add element to frame
titlePage.pack()


# amount
amountLabel = Label(currencyApp, text='Enter Currency Amount', font=('Arial', 14))

amountLabel.place(x=20, y=100)
amountInput = StringVar(currencyApp)
amount = Entry(currencyApp, width=10, font=('Arial', 15), textvariable=amountInput)
amount.place(x=270, y=100)


# Currencies list
CurrencyCodes_List = ['EUR', 'IDR', 'BGN', 'ILS', 'GBP', 'DKK', 'CAD', 'JPY', 'HUF', 'RON', 'MYR', 'SEK', 'SGD', 'HKD', 'AUD', 'CHF', 'KRW', 'CNY', 'TRY', 'HRK', 'NZD', 'THB', 'USD', 'NOK',
                      'RUB', 'INR', 'MXN', 'CZK', 'BRL', 'PLN', 'PHP', 'ZAR']
# From
fromLabel = Label(currencyApp, text='Select Currency From', font=('Arial', 14))
fromLabel.place(x=20, y=150)


in1 = StringVar(currencyApp)
in1.set('Select')
fromOption = OptionMenu(currencyApp, in1, *CurrencyCodes_List)
fromOption.place(x=300, y=150)

# to
toLabel = Label(currencyApp, text='Select Currency To', font=('Arial', 14))

toLabel.place(x=20, y=200)


in2 = StringVar(currencyApp)
in2.set('Select')
toOption = OptionMenu(currencyApp, in2, *CurrencyCodes_List)
toOption.place(x=300, y=200)



# output
output = Entry(currencyApp, width=10, font=('Arial', 15))

output.place(x=270, y=330)

# function convert
def convert():
    c = CurrencyRates()
    value = amountInput.get()
    fromCurrency = in1.get()
    toCurrency = in2.get()
    if value == '':
        messagebox.showerror('Error', 'please enter value you want to convert it')
    elif (fromCurrency == "Select" or toCurrency == "Select"):
        messagebox.showerror('Error', 'please select option')
    else :
        res = c.convert(fromCurrency, toCurrency,  float(value))
        result = float('{:.4f}'.format(res))
        #print(result)
        output.delete(0, END)
        output.insert(0, str(result))

# button convert
btnConvert = Button(currencyApp, text='Convert', width=20, height=2, bg='#e91e63', fg='white', command=convert)

btnConvert.place(x=170, y=250)



# answer
resultLabel = Label(currencyApp, text='Result Currency Is ', font=('Arial', 14))

resultLabel.place(x=20, y=330)









# Run app infinity
currencyApp.mainloop()