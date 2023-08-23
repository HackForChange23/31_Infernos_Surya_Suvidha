from flask import Flask,request,jsonify
from chatbot_run import  create_chatbot_executor
import pickle
import numpy as np



app=Flask(__name__)


@app.route('/')

def home():
    return "Hello World"

@app.route('/predict',methods=['POST'])

def predict():
    message=request.form.get('message')
    result=create_chatbot_executor(message)
    return jsonify({'response':result})

if __name__=='__main__':
    app.run(debug=True)