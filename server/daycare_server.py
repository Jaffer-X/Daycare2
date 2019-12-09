import datetime
import random
import json
import pymysql
import math
from sklearn import tree
from sklearn.tree import DecisionTreeClassifier
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn import svm
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.neighbors import KNeighborsClassifier
from http.server import HTTPServer, BaseHTTPRequestHandler

class assignment_core(object):
    host = "119.3.209.144"
    port = 3306
    user = "csye6200"
    passwd = "!Csye6200"
    db = "DayCare"
    clf_svm = svm.SVC(gamma='scale')
    clf_dt= tree.DecisionTreeClassifier()
    def train(self):
        x_data = []
        y_tag = []
        conn_read = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
        cursor_read = conn_read.cursor()
        read_sql = ("SELECT s.Gender,s.ReadTest,s.SportTest,s.MathTest,t.GenderFeature,t.ReadingFeature,t.SportFeature,t.MathFeature,r.Rank from Basic_Student s,Basic_Teacher t, Basic_TeachingRecord r "  # 6
                    " where r.TeacherId=t.TeacherId and r.StudentId=s.StudentId and r.Rank is not null;")
        print(read_sql)
        row_account = cursor_read.execute(read_sql)
        print(row_account)
        for rx in range(0, row_account):
            row = cursor_read.fetchone()
            tmp_content = []
            if row[0] == "male":
                tmp_gender = 100
            else:
                tmp_gender = -100
            tmp_content.append(tmp_gender)
            for bx in range(1, 8):
                if row[bx] is None:
                    tmp_content.append(0)
                else:
                    tmp_content.append(float(row[bx]))
            tmp_class=int(row[8])
            x_data.append(tmp_content)
            y_tag.append(tmp_class)
        print(str(len(x_data)) + "," + str(len(y_tag)) + ".")
        conn_read.commit()
        cursor_read.close()
        conn_read.close()
        self.clf_svm.fit(x_data, y_tag)
        self.clf_dt.fit(x_data, y_tag)

    def algorithm_basic(self,age,gender,read_score,sport_score,math_score):
        id = -1
        conn_read = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
        cursor_read = conn_read.cursor()
        tmp_grade=math.ceil(int(age)/12)
        volum=[4,5,6,8,12,15]
        tmp_volumn=volum[tmp_grade-1]
        read_sql = ("SELECT distinct t.TeacherID,t.GenderFeature,t.ReadingFeature,t.SportFeature,t.MathFeature from Basic_Teacher t,Basic_TeachingRecord r "
                    " where (r.Period = '2019' and r.TeacherID = t.TeacherId and r.Grade="+str(tmp_grade)+" and t.TeacherID in "
                    " (SELECT ma from (SELECT TeacherId ma,count(TeacherId) mb from Basic_TeachingRecord  group by TeacherId) m where m.mb<"+str(tmp_volumn)+")) or"
                    " t.TeacherID not in (SELECT TeacherID from Basic_TeachingRecord where Period='2019') order by t.TeacherID;")
        print(read_sql)
        row_account = cursor_read.execute(read_sql)
        print(row_account)
        if row_account!=0:
            row = cursor_read.fetchone()
            id=row[0]
        conn_read.commit()
        cursor_read.close()
        conn_read.close()
        return id

    def algorithm_svm(self,age,gender,read_score,sport_score,math_score):
        id = -1
        conn_read = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
        cursor_read = conn_read.cursor()
        tmp_grade=math.ceil(int(age)/12)
        volum=[4,5,6,8,12,15]
        tmp_volumn=volum[tmp_grade-1]
        read_sql = ("SELECT distinct t.TeacherID,t.GenderFeature,t.ReadingFeature,t.SportFeature,t.MathFeature from Basic_Teacher t,Basic_TeachingRecord r "
                    " where (r.Period = '2019' and r.TeacherID = t.TeacherId and r.Grade="+str(tmp_grade)+" and t.TeacherID in "
                    " (SELECT ma from (SELECT TeacherId ma,count(TeacherId) mb from Basic_TeachingRecord  group by TeacherId) m where m.mb<"+str(tmp_volumn)+")) or"
                    " t.TeacherID not in (SELECT TeacherID from Basic_TeachingRecord where Period='2019') order by t.TeacherID;")
        print(read_sql)
        row_account = cursor_read.execute(read_sql)
        print(row_account)
        high_score = 0
        for rx in range(0, row_account):
            row = cursor_read.fetchone()
            if gender == "male":
                tmp_gender = 100
            else:
                tmp_gender = -100
            tmp_content = []
            tmp_content.append(tmp_gender)
            tmp_content.append(read_score)
            tmp_content.append(sport_score)
            tmp_content.append(math_score)
            tmp_content.append(row[1])
            tmp_content.append(row[2])
            tmp_content.append(row[3])
            tmp_content.append(row[4])
            score = self.clf_svm.predict([tmp_content])
            print(str(score)+str(row))
            if score>high_score:
                high_score=score
                id=int(row[0])
        print("HighestScore=" + str(high_score) + ", TeacherId=" + str(id))
        conn_read.commit()
        cursor_read.close()
        conn_read.close()
        return id
    def algorithm_dt(self,age,gender,read_score,sport_score,math_score):
        id = -1
        conn_read = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
        cursor_read = conn_read.cursor()
        tmp_grade=math.ceil(int(age)/12)
        volum=[4,5,6,8,12,15]
        tmp_volumn=volum[tmp_grade-1]
        read_sql = ("SELECT distinct t.TeacherID,t.GenderFeature,t.ReadingFeature,t.SportFeature,t.MathFeature from Basic_Teacher t,Basic_TeachingRecord r "
                    " where (r.Period = '2019' and r.TeacherID = t.TeacherId and r.Grade="+str(tmp_grade)+" and t.TeacherID in "
                    " (SELECT ma from (SELECT TeacherId ma,count(TeacherId) mb from Basic_TeachingRecord  group by TeacherId) m where m.mb<"+str(tmp_volumn)+")) or"
                    " t.TeacherID not in (SELECT TeacherID from Basic_TeachingRecord where Period='2019') order by t.TeacherID;")
        print(read_sql)
        row_account = cursor_read.execute(read_sql)
        print(row_account)
        high_score=0
        for rx in range(0, row_account):
            row = cursor_read.fetchone()
            if gender == "male":
                tmp_gender = 100
            else:
                tmp_gender = -100
            tmp_content = []
            tmp_content.append(tmp_gender)
            tmp_content.append(read_score)
            tmp_content.append(sport_score)
            tmp_content.append(math_score)
            tmp_content.append(row[1])
            tmp_content.append(row[2])
            tmp_content.append(row[3])
            tmp_content.append(row[4])
            score = self.clf_dt.predict([tmp_content])
            print("Score="+str(score)+", TeacherFeature="+str(row))
            if score>high_score:
                high_score=score
                id=int(row[0])
        print("HighestScore=" + str(high_score) + ", TeacherId=" + str(id))
        conn_read.commit()
        cursor_read.close()
        conn_read.close()
        return id

class MRequest(BaseHTTPRequestHandler):
    host = "119.3.209.144"
    port = 3306
    user = "csye6200"
    passwd = "!Csye6200"
    db = "DayCare"
    core=assignment_core()
    core.train()
    def get_message(self,name,age,parentName,address,parentPhone,gender,read,sport,math2,algorithm):
        id = 0
        if algorithm == "0":
            id = self.core.algorithm_basic(age,gender,read, sport, math2)
        elif algorithm == "1":
            id = self.core.algorithm_svm(age,gender,read, sport, math2)
        elif algorithm == "2":
            id = self.core.algorithm_dt(age,gender,read, sport, math2)

        if id != -1:
            #insert student
            conn_write = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
            cursor_write = conn_write.cursor()
            write_sql = ("insert into Basic_Student SET name='"+name+"',age='"+age+"', grade='"+str(math.ceil(int(age)/12))+"', parentName='"+parentName+"', parentPhone='"+parentPhone+"',Address='"+address+"',"
                        " Gender='"+gender+"',ReadTest='"+read+"',SportTest='"+sport+"',MathTest='"+math2+"';")
            print(write_sql)
            result = cursor_write.execute(write_sql)
            conn_write.commit()
            cursor_write.close()
            conn_write.close()
            #insert TeachingRecord
            #get studentID
            conn_read = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                        passwd=self.passwd, db=self.db, charset='utf8')
            cursor_read = conn_read.cursor()
            read_sql = ("SELECT * from Basic_Student where name='"+name+"'and age='"+age+"' and parentName='"+parentName+"' and parentPhone='"+parentPhone+"' order by timestamp desc;")
            print(read_sql)
            row_account = cursor_read.execute(read_sql)
            print(row_account)
            row = cursor_read.fetchone()
            studentId=row[0]
            conn_read.commit()
            cursor_read.close()
            conn_read.close()
            #get GroupID
            conn_read = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                        passwd=self.passwd, db=self.db, charset='utf8')
            cursor_read = conn_read.cursor()
            read_sql = ("SELECT GroupId from Basic_TeachingRecord where TeacherId='"+str(id)+"';")
            print(read_sql)
            row_account = cursor_read.execute(read_sql)
            if row_account>0:
                row = cursor_read.fetchone()
                groupId=row[0]
            else:
                conn_read2 = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                            passwd=self.passwd, db=self.db, charset='utf8')
                cursor_read2 = conn_read2.cursor()
                read_sql2 = ("SELECT max(GroupId) from Basic_TeachingRecord;")
                print(read_sql2)
                row_account2 = cursor_read2.execute(read_sql2)
                row2 = cursor_read2.fetchone()
                groupId = row2[0]+1
                conn_read2.commit()
                cursor_read2.close()
                conn_read2.close()
            conn_read.commit()
            cursor_read.close()
            conn_read.close()
            #get ClassroomID
            conn_read = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                        passwd=self.passwd, db=self.db, charset='utf8')
            cursor_read = conn_read.cursor()
            read_sql = ("SELECT ClassroomId from Basic_TeachingRecord where TeacherId='"+str(id)+"';")
            print(read_sql)
            row_account = cursor_read.execute(read_sql)
            if row_account>0:
                row = cursor_read.fetchone()
                classroomId=row[0]
            else:
                conn_read2 = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                            passwd=self.passwd, db=self.db, charset='utf8')
                cursor_read2 = conn_read2.cursor()
                tmp_grade=int(math.ceil(int(age)/12))
                if tmp_grade==1:
                    tmp_class=4
                elif tmp_grade ==2:
                    tmp_class=5
                elif tmp_grade == 3:
                    tmp_class = 6
                elif tmp_grade ==4:
                    tmp_class=8
                elif tmp_grade ==5:
                    tmp_class=12
                elif tmp_grade ==6:
                    tmp_class=15
                else:
                    tmp_class=0
                read_sql2 = ("SELECT m.ma from (SELECT ClassroomID ma, count(ClassroomID)mb from Basic_TeachingRecord where grade="+str(tmp_grade)+" GROUP by ClassroomId)m where m.mb<"+str(tmp_class)+";")
                print(read_sql2)
                row_account2 = cursor_read2.execute(read_sql2)
                if row_account2>0:
                    row2 = cursor_read2.fetchone()
                    classroomId = row2[0]
                else:
                    conn_read3 = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                                 passwd=self.passwd, db=self.db, charset='utf8')
                    cursor_read3 = conn_read3.cursor()
                    read_sql3 = ("SELECT max(ClassroomId) from Basic_TeachingRecord;")
                    print(read_sql3)
                    row_account3 = cursor_read3.execute(read_sql3)
                    row3 = cursor_read3.fetchone()
                    classroomId = row3[0] + 1
                    conn_read3.commit()
                    cursor_read3.close()
                    conn_read3.close()
                conn_read2.commit()
                cursor_read2.close()
                conn_read2.close()
            conn_read.commit()
            cursor_read.close()
            conn_read.close()
            #write
            conn_write = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
            cursor_write = conn_write.cursor()
            write_sql = ("insert into Basic_TeachingRecord SET StudentId='"+str(studentId)+"',TeacherId='"+str(id)+"', GroupId='"+str(groupId)+"', ClassroomId='"+str(classroomId)+
                         "',Grade='"+str(int(math.ceil(int(age)/12)))+"',Period='2019';")
            print(write_sql)
            result = cursor_write.execute(write_sql)
            conn_write.commit()
            cursor_write.close()
            conn_write.close()
            #immunization
            conn_read = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                        passwd=self.passwd, db=self.db, charset='utf8')
            cursor_read = conn_read.cursor()
            read_sql = ("select StudentId from Basic_Student where StudentId="+str(studentId)+";")
            print(read_sql)
            row_account = cursor_read.execute(read_sql)
            print(row_account)
            for rx in range(0, row_account):
                row = cursor_read.fetchone()
                conn_read2 = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                             passwd=self.passwd, db=self.db, charset='utf8')
                cursor_read2 = conn_read2.cursor()
                read_sql2 = ("select VaccineId,Dose from Basic_Vaccine where Category = 'Childcare';")
                print(read_sql2)
                row_account2 = cursor_read2.execute(read_sql2)
                print(row_account2)
                for bx in range(0, row_account2):
                    row2 = cursor_read2.fetchone()
                    for cx in range(0, int(row2[1])):
                        conn_write = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                                     passwd=self.passwd, db=self.db, charset='utf8')
                        cursor_write = conn_write.cursor()
                        tmp_alert=""
                        if cx==0:
                            tmp_alert="upcoming"
                        write_sql = ("insert Basic_ImmunizationRecord SET StudentId='" + str(
                            row[0]) + "',VaccineId='" + str(row2[0]) + "',batch='" + str(cx + 1) +
                                     "',alert='" + str(tmp_alert) + "',status='" + str("not taken") + "',period='2019';")
                        print(write_sql)
                        # print(rx)
                        self.result = cursor_write.execute(write_sql)
                        conn_write.commit()
                        cursor_write.close()
                        conn_write.close()
                conn_read2.commit()
                cursor_read2.close()
                conn_read2.close()
            conn_read.commit()
            cursor_read.close()
            conn_read.close()
        return id

    def do_POST(self):
        length = int(self.headers['Content-Length'])
        m_json=json.loads(str(self.rfile.read(length).decode('utf-8')))
        print(m_json)
        m_content = {'teacherID': self.get_message(str(m_json['name']),str(m_json['age']),str(m_json['parentName']),str(m_json['address']),str(m_json['parentPhone']),
                                                   str(m_json['gender']),str(m_json['read']), str(m_json['sport']), str(m_json['math']), str(m_json['algorithm']))}
        print(m_content)
        self.send_response(200)
        self.send_header('Content-type', 'application/json')
        self.end_headers()
        self.wfile.write(json.dumps(m_content).encode())

class http_server(object):
    host = ('192.168.3.45', 6200)
    def start(self):
        server = HTTPServer(self.host, MRequest)
        print("Starting server, listen at: %s:%s" % self.host)
        server.serve_forever()

if __name__ == '__main__':
        server = http_server()
        server.start()
        # mas=assignment_core()
        # mas.train()
        # mas.algorithm_svm(26,"male",100,50,40)
        # mas.algorithm_dt(26,"male",100,50,40)