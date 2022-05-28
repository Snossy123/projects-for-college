# import needed libraries
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.preprocessing import LabelEncoder

df = pd.read_csv("assignment2_dataset_cars.csv")
df.head()

print(df.shape)

# Data Cleaning
print(df.car_maker.isna().sum())
print(df.price.isna().sum())
print(df.kilometers.isna().sum())
print(df.year.isna().sum())

print(df.duplicated().sum())
df = df.drop_duplicates()
print(df.duplicated().sum())
print(df.car_maker.unique())
print(df.car_maker.value_counts())
car_maker_col = list(df.car_maker.unique())
df = pd.get_dummies(df)


def featureScaling(X,a,b):
    X = np.array(X)
    Normalized_X=np.zeros((X.shape[0],X.shape[1]))
    for i in range(X.shape[1]):
        Normalized_X[:,i]=((X[:,i]-min(X[:,i]))/(max(X[:,i])-min(X[:,i])))*(b-a)+a
    return Normalized_X

input_cols = ['year', 'kilometers','Toyota','Kia',
 'Ford','Renault','Nissan','BMW','Cadillac',
 'Mazda','GMC','Dodge','Suzuki'] 
X = featureScaling(df.iloc[:, df.columns != 'price'], 0,1)
print(X.shape)
X = np.array(X)  
X = pd.DataFrame(X, columns =input_cols)
X.head()

# Feature Select
y = df.iloc[:, -1]
X_names = X.columns
r_vals = []
for col_name in X_names:
    x = X.loc[:, col_name]
    r_val = np.corrcoef(x, y)[0,1]
    print('R value between', col_name, 'and Y =', r_val)
    r_vals.append(r_val)

# This function will get the indices of the max 2 values in r_values
# these indices are equivalent to the indices of the parameters in X with the highest r_values
indices = np.argpartition(r_vals, 3)[3:] 
feature_selected = []
for i in indices:
    print(X_names[i])
    feature_selected.append(i)
X = X.iloc[:, feature_selected]    
    

# Part Two: Linear Regression Model
# Now we have two variables X1, and X2.

# Our Linear Regression Model is Y = a0 + a1 * X1 + a2 * X2
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LinearRegression
from sklearn import metrics
from sklearn.model_selection import train_test_split  
from sklearn import linear_model   
from sklearn.model_selection import cross_val_score

X_train, X_test, y_train, y_test = train_test_split(
    X , y, test_size=0.2, random_state=0)

model_2_poly_features = PolynomialFeatures(degree=4)
# transforms the existing features to higher degree features.
X_train_poly_model_2 = model_2_poly_features.fit_transform(X_train)
X_test_poly_model_2 = model_2_poly_features.fit_transform(X_test)
# fit the transformed features to Linear Regression
poly_model2 = linear_model.LinearRegression()
scores = cross_val_score(poly_model2, X_train_poly_model_2, y_train, scoring='neg_mean_squared_error', cv=5)
model_2_score = abs(scores.mean())
poly_model2.fit(X_train_poly_model_2, y_train) 
prediction = poly_model2.predict(X_test_poly_model_2)
print('Mean Square Error in training', np.sqrt(metrics.mean_squared_error(y_test, prediction)))
print("model 2 cross validation score is "+ str(model_2_score))
