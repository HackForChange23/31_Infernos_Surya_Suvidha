from flask import Flask,request,jsonify
import pickle
import numpy as np



label_mapping={20: 'rice',
 11: 'maize',
 3: 'chickpea',
 9: 'kidneybeans',
 18: 'pigeonpeas',
 13: 'mothbeans',
 14: 'mungbean',
 2: 'blackgram',
 10: 'lentil',
 19: 'pomegranate',
 1: 'banana',
 12: 'mango',
 7: 'grapes',
 21: 'watermelon',
 15: 'muskmelon',
 0: 'apple',
 16: 'orange',
 17: 'papaya',
 4: 'coconut',
 6: 'cotton',
 8: 'jute',
 5: 'coffee'}


model=  pickle.load(open('model.pkl','rb'))
app=Flask(__name__)


@app.route('/')

def home():
    return "Hello World"


@app.route('/predict',methods=['POST'])


def predict():
    N=request.form.get('N')
    P=request.form.get('P')
    K=request.form.get('K')
    temperature=request.form.get("temperature")
    humidity=request.form.get("humidity")
    ph=request.form.get('ph')
    rainfall=request.form.get('rainfall')
    input_query=np.array([[N,P,K,temperature,humidity,ph,rainfall]])
    result=model.predict(input_query)[0]
    return jsonify({'crop':label_mapping[result]})



if __name__=='__main__':
    app.run(debug=True)