
import time

mytime1 = time.ctime()
# Thu Apr 29 15:34:40 2021
# print(mytime1)

mytime2 = time.asctime()
# Thu Apr 29 15:35:46 2021
# print(mytime2)
def timeFun():
    mytime3 = time.localtime()
    return time.strftime('%d/%m/%Y,  %H:%M:%S', mytime3)

# 29/04/2021,  15:39:11
# print(mytimeformate)

# =========================Sleep Function====================================
# beforeSleep = print(timeFun())
# time.sleep(3)
# afterSleep = print(timeFun())
# 29/04/2021,  16:00:06
# 29/04/2021,  16:00:09


# =========================Datetime====================================
from datetime import datetime
x = datetime.now();
# print(x)
# 2021-05-01 00:44:25.766537
y = x.strftime('%d/%B/%Y')
# 01/May/2021
# print(y)