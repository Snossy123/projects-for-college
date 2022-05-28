# ---
# jupyter:
#   jupytext:
#     formats: ipynb,py:percent
#     text_representation:
#       extension: .py
#       format_name: percent
#       format_version: '1.3'
#       jupytext_version: 1.13.8
#   kernelspec:
#     display_name: Python 3 (ipykernel)
#     language: python
#     name: python3
# ---

# %% [markdown]
# # Assignment ML 
#   `Genrel workFlow`
# - import essential libraries
# - Reading input files to create
#     training dataset
# - Create variables for dataset
#     - extract HOG feature from image
#     - Create variable for dataset
# - Get HOG feature from each
#     image & put into dataset
#     variable
# - Build SVM Model
#     - Train model
#     - test model
#         - prepare test data
#         - test model
#     - Evalute Model

# %% [markdown] tags=[]
# ## import essential libraries 

# %%
import numpy as np
import pandas as pd
import os
import random
from skimage.transform import resize
from skimage.feature import hog
import cv2
from sklearn import datasets
from sklearn import svm

# %% [markdown] tags=[]
# ## Reading input files to create training dataset


filenames = os.listdir('data1/')
filenames = filenames[:1100] + filenames[-1100:]
# filenames
categories = []

for filename in filenames:
    
    category = filename.split('.')[0]
    
    if(category == 'dog'):
        
        categories.append('dog')
        
    else:
        
        categories.append('cat')
        
# create a dataframe

df = pd.DataFrame({
        
        'filename' : filenames,
    
        'category' : categories
    
    })
# Showing some data files
# df.head()

# %%
# Checking which categories of data is present : mainly done to check the balancing of data
# df['category'].value_counts()

# %%
# Sample image
sample = random.choice(filenames)

# print(sample)
# Using cv2.imread() method
# Using 0 to read image in grayscale mode
image = cv2.imread('train/' + sample, 0)
# print(image)
# # # Displaying the image
# cv2.imshow('image', image)

# %%
# Using 20% of your data for testing and 80% for training
train_no = list(range(100))
train_no = train_no + list(range(2100,2200)) 
train_df, validate_df = df.iloc[100:2100, :], df.iloc[train_no, :]
# train_test_split(df, test_size = 0.09, random_state = 42)

train_df = train_df.sample(frac=1).reset_index(drop=True)

validate_df = validate_df.sample(frac=1).reset_index(drop=True)
# print(train_df.shape)
# print(validate_df.shape)

# # %%
# train_df['category'].value_counts()
#
# # %%
# train_df['category'].head()
#
# # %%
# validate_df['category'].value_counts()
#
# # %%
# validate_df['category'].head()

# %%
total_train = train_df.shape[0]

total_validate = validate_df.shape[0]

print("Total number of example in training dataset : {0}".format(total_train))

print("Total number of example in validation dataset : {0}".format(total_validate))

# %% [markdown]
# ## Create variables for dataset

# %% [markdown] tags=[]
# ### test extract HOG feature from image
#
#  
#

# %%
# Test extract HOG feature from image
path = r'train/' + sample
 
# Using 0 to read image in grayscale mode
img = cv2.imread(path)

# resize image
resized_img = resize(img, (128, 64))

# creating hog features
fd, hog_image = hog(resized_img, orientations=9, pixels_per_cell=(8, 8),
                    cells_per_block=(2, 2), visualize=True, multichannel=True)
# fd.shape

# %%
n_dims = fd.shape[0]
n_samples = train_df.shape[0]

# %%
# n_dims
#
# # %%
# n_samples

# %% [markdown]
# ### Create variable for dataset

# %%
X_train, y_train = datasets.make_classification(n_samples=n_samples, n_features=n_dims)

# %%
X_test, y_test = datasets.make_classification(n_samples=validate_df.shape[0], n_features=n_dims)

# # %%
# X_train.shape
#
# # %%
# y_train.shape
#
# # %%
# X_test.shape
#
# # %%
# y_test.shape


# %% [markdown]
# ## Get HOG feature from each image & put into dataset variable

# %%
def HOG_feature(X, img_name):
    img_dir = img_name
    for pos in range(len(img_dir)):
        # path
        path = r'train/' + img_dir[pos]

        # Using cv2.imread() method
        # Using 0 to read image in grayscale mode
        img = cv2.imread(path)

        # resize image
        resized_img = resize(img, (128, 64))

        # creating hog features
        fd, hog_image = hog(resized_img, orientations=9, pixels_per_cell=(8, 8),
                            cells_per_block=(2, 2), visualize=True, multichannel=True)

        # update feature of image 
        X[pos] = fd

    return X


# %%
X_train = HOG_feature(X_train, train_df['filename'])

# # %%
# X_train.shape
#
# # %%
# X_train[0]

# %%
# encoding y labels cat = 0 & dog = 1
y_train = [0  if i == 'cat' else 1 for i in train_df['category']]
# y_train[:10]

# %%
# train_df['category'].head(10)

# %% [markdown]
# # Build SVM Model

# %% [markdown]
# ## Train model

# %%
# we create an instance of SVM and fit out data.
C = 0.001  # SVM regularization parameter
svc = svm.SVC(kernel='linear', C=C).fit(X_train, y_train)
lin_svc = svm.LinearSVC(C=C).fit(X_train, y_train)
rbf_svc = svm.SVC(kernel='rbf', gamma=0.8, C=C).fit(X_train, y_train)
poly_svc = svm.SVC(kernel='poly', degree=3, C=C).fit(X_train, y_train)

# %% [markdown]
# ## test model

# %% [markdown] tags=[]
# #### prepare test data

# %%
X_test = HOG_feature(X_test, validate_df['filename'])
# X_test.shape

# %%
# encoding y labels cat = 0 & dog = 1
y_test = [0  if i == 'cat' else 1 for i in validate_df['category']]
# y_test[:10]

# %% [markdown]
# #### test model

# %%
acc = []
for i, clf in enumerate((svc, lin_svc, rbf_svc, poly_svc)):
    predictions = clf.predict(X_train)
    acc.append(np.mean(predictions == y_train))

# %% [markdown]
# ## Evalute Model

# %%
# Calculate the accuracy score: score
print('train accuracy using SVC kernel=linear :',acc[0])
print('train accuracy using LinearSVC :',acc[1])
print('train accuracy using SVC kernel=rbf :',acc[2])
print('train accuracy using svc kernel=poly :',acc[3])

# #### test model

# %%
acc = []
for i, clf in enumerate((svc, lin_svc, rbf_svc, poly_svc)):
    predictions = clf.predict(X_test)
    acc.append(np.mean(predictions == y_test))

# %% [markdown]
# ## Evalute Model

# %%
# Calculate the accuracy score: score
print('test accuracy using SVC kernel=linear :',acc[0])
print('test accuracy using LinearSVC :',acc[1])
print('test accuracy using SVC kernel=rbf :',acc[2])
print('test accuracy using svc kernel=poly :',acc[3])
