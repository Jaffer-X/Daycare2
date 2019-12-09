import requests
import json
import time
import random
url = "http://119.3.209.144:6200"
for i in range(0,10):
    names=['Kelly','Addison','Alex','Tommy','Joyce','Andrew','Alonso','Karen','Denny',
           'Kenney','Colin','Warren','Ben','Carl','Charles','Easter','Bill','Glen','Alva',
           'Roger','Solomon','Paul','Randy','Tina','Wesley','Fred','Leon','James','Bruce',
           'Benson','Barry','Amy','Nico','Ekko','Zed','Xin','Yi','Master','Aattrox','Blues','Gloria','Emma',
           'Lucy','Jenney','May','Kate','Sophia','Leon']
    postdata = {'name':str(random.choice(names)+random.choice(names)),'age':random.randint(6, 70),'parentName':str(random.choice(names)+random.choice(names)),
                'address':str(random.choice(names)+" St."+ str(random.randint(1,10)))+"Apt.",'parentPhone':str(random.randint(1000000000,9999999999)),'gender':str(random.choice(['male','female'])),
                'read':str(random.randint(0,100)),'sport':str(random.randint(0,100)),'math':str(random.randint(0,100)),'algorithm':'0'}
    print(postdata)
    headers = {
    'Accept': '*/*',
    'Accept-Language': 'zh-CN',
    'Content-Type': 'application/json',
    'Accept-Encoding': 'gzip, deflate',
    'User-Agent': 'Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)',
    'Pragma': 'no-cache',
    'Content-Length': str(len(postdata))
    }
    time1 = time.time()
    req = requests.post(url, data=json.dumps(postdata), headers=headers)
    print(req.status_code)
    print(req.text)
    time2=time.time()
    print(time2-time1)