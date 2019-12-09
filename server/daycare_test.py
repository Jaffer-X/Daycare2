import datetime
import random
import json
import pymysql
import sys
import math

class fill_database():
    host = "119.3.209.144"
    port = 3306
    user = "csye6200"
    passwd = "!Csye6200"
    db = "DayCare"
    names=['Kelly','Addison','Alex','Tommy','Joyce','Andrew','Alonso','Karen','Denny',
           'Kenney','Colin','Warren','Ben','Carl','Charles','Easter','Bill','Glen','Alva',
           'Roger','Solomon','Paul','Randy','Tina','Wesley','Fred','Leon','James','Bruce',
           'Benson','Barry','Amy','Nico','Ekko','Zed','Xin','Yi','Master','Aattrox','Blues','Gloria','Emma',
           'Lucy','Jenney','May','Kate','Sophia','Leon']
    def fill_teacher(self,amount):
        for rx in range(0, amount):
            conn_write = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
            cursor_write = conn_write.cursor()
            write_sql = ("insert into Basic_Teacher SET name='"+str(random.choice(self.names)+random.choice(self.names))+"',age='"+str(random.randint(25, 65))+"', wage='"+str(random.randint(1000, 10000))+"';")
            print(str(rx)+"："+write_sql)
            #print(rx)
            self.result = cursor_write.execute(write_sql)
            conn_write.commit()
            cursor_write.close()
            conn_write.close()

    def fill_student(self,amount):
        for rx in range(0, amount):
            conn_write = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
            cursor_write = conn_write.cursor()
            age = random.randint(6, 70)
            if age>=6 and age<=12:
                grade=1
            elif age>=13 and age<=24:
                grade=2
            elif age>=25 and age<=36:
                grade=3
            elif age>=37 and age<=48:
                grade=4
            elif age>=49 and age<=60:
                grade=5
            elif age>=61:
                grade=6
            else:
                grade=6
            write_sql = ("insert into Basic_Student SET Name='"+str(random.choice(self.names)+random.choice(self.names))+"',Age='"+str(age)+"', Grade='"+str(grade)+
                         "',ParentName='"+str(random.choice(self.names)+random.choice(self.names))+"',Address='"+str(random.choice(self.names)+" St."+ str(random.randint(1,10)))+"Apt."+
                         "',parentPhone='"+str(random.randint(1000000000,9999999999))+"',Gender='"+str(random.choice(['male','female']))+"',ReadTest='"+str(random.randint(0,100))+
                         "',SportTest='"+str(random.randint(0,100))+"',MathTest='"+str(random.randint(0,100))+"',Period='2018';")
            print(str(rx)+"："+write_sql)
            #print(rx)
            self.result = cursor_write.execute(write_sql)
            conn_write.commit()
            cursor_write.close()
            conn_write.close()

    def fill_classroom(self,amount):
        for rx in range(0, amount):
            conn_write = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
            cursor_write = conn_write.cursor()
            cap = random.randint(50, 100)
            write_sql = ("insert into Basic_Classroom SET Name='"+str(random.choice(self.names)+random.choice(self.names)+" Hall")+"',Capacity='"+str(cap)+"', Remain='"+str(cap)+"';")
            print(str(rx)+"："+write_sql)
            #print(rx)
            self.result = cursor_write.execute(write_sql)
            conn_write.commit()
            cursor_write.close()
            conn_write.close()

    def fill_teaching_record(self):
        for rx in range(1, 501):
            for bx in range(0,4):
                conn_write = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
                cursor_write = conn_write.cursor()
                cap = random.randint(50, 100)
                write_sql = ("insert into Basic_TeachingRecord SET StudentId='"+str((rx-1)*4+bx)+"',TeacherId='"+str(rx)+"', GroupId='"+str(rx)+"', ClassroomId='"+str(int(math.floor((rx-1)/4)))+"', Rank='"+str(random.randint(0,100))+"',Grade=1;")
                print(str(rx)+"："+write_sql)
                #print(rx)
                self.result = cursor_write.execute(write_sql)
                conn_write.commit()
                cursor_write.close()
                conn_write.close()

    def fill_immunization_record(self):
        conn_read = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
        cursor_read = conn_read.cursor()
        read_sql = ("select StudentId from Basic_Student;")
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
            for bx in range(0,row_account2):
                row2=cursor_read2.fetchone()
                for cx in range(0,int(row2[1])):
                    conn_write = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                                 passwd=self.passwd, db=self.db, charset='utf8')
                    cursor_write = conn_write.cursor()
                    write_sql = ("insert Basic_ImmunizationRecord SET StudentId='"+str(row[0])+"',VaccineId='"+str(row2[0])+"',batch='"+str(cx+1)+
                                 "',alert='"+str(random.choice(['upcoming','overdue',' ']))+"',status='"+str(random.choice(['taken','not taken']))+"',period='2018';")
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

    def calculate_teacher_feature(self):
        conn_read = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
        cursor_read = conn_read.cursor()
        read_sql = ("select TeacherId from Basic_Teacher;")
        print(read_sql)
        row_account = cursor_read.execute(read_sql)
        print(row_account)
        for rx in range(0, row_account):
            row = cursor_read.fetchone()
            conn_read2 = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                        passwd=self.passwd, db=self.db, charset='utf8')
            cursor_read2 = conn_read2.cursor()
            read_sql2 = ("select sum(Rank) from Basic_TeachingRecord where TeacherID="+str(row[0])+";")
            print(read_sql2)
            row_account2 = cursor_read2.execute(read_sql2)
            print(row_account2)
            row2=cursor_read2.fetchone()
            total_rank=int(row2[0])
            conn_read2.commit()
            cursor_read2.close()
            conn_read2.close()
            #
            conn_read2 = pymysql.connect(host=self.host, port=self.port, user=self.user,
                                        passwd=self.passwd, db=self.db, charset='utf8')
            cursor_read2 = conn_read2.cursor()
            read_sql2 = ("select Basic_Student.Gender,Basic_Student.ReadTest,Basic_Student.SportTest,Basic_Student.MathTest,Basic_TeachingRecord.Rank from Basic_TeachingRecord,Basic_Student "
                         "where Basic_Student.StudentID=Basic_TeachingRecord.StudentID and Basic_TeachingRecord.TeacherID="+str(row[0])+";")
            print(read_sql2)
            row_account2 = cursor_read2.execute(read_sql2)
            print(row_account2)
            gender=0
            read=0
            sport=0
            math=0
            for bx in range(0,row_account2):
                row2=cursor_read2.fetchone()
                #print(row2)
                if str(row2[0])=='male':
                    male=100
                else:
                    male=-100
                gender = gender + float(row2[4])/total_rank*float(male)
                read = read + float(row2[4])/total_rank*float(row2[1])
                sport = sport + float(row2[4])/total_rank*float(row2[2])
                math = math +float(row2[4]) / total_rank * float(row2[3])
            conn_read2.commit()
            cursor_read2.close()
            conn_read2.close()
            #write
            conn_write = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
            cursor_write = conn_write.cursor()
            write_sql = ("update Basic_Teacher SET GenderFeature='"+str(gender)+"',ReadingFeature='"+str(read)+"',"
                            " SportFeature='"+str(sport)+"',MathFeature='"+str(math)+"' where TeacherId = "+str(row[0])+";")
            print(write_sql)
            #print(rx)
            self.result = cursor_write.execute(write_sql)
            conn_write.commit()
            cursor_write.close()
            conn_write.close()
        conn_read.commit()
        cursor_read.close()
        conn_read.close()

    def truncate_table(self,table_name):
        conn_write = pymysql.connect(host=self.host, port=self.port,user=self.user,
                                         passwd=self.passwd, db=self.db, charset='utf8')
        cursor_write = conn_write.cursor()
        write_sql = ("truncate table "+ table_name +";")
        print(write_sql)
        self.result = cursor_write.execute(write_sql)
        conn_write.commit()
        cursor_write.close()
        conn_write.close()

    def clear_table(self):
        self.truncate_table("Basic_Student")
        self.truncate_table("Basic_Teacher")
        self.truncate_table("Basic_ImmunizationRecord")
        self.truncate_table("Basic_TeachingRecord")
        self.truncate_table("Basic_Classroom")

    def fill_table(self):
        self.fill_teacher(500)
        self.fill_student(2000)
        self.fill_classroom(500)
        self.fill_teaching_record()
        self.calculate_teacher_feature()
        self.fill_immunization_record()

    def start(self):
        if len(sys.argv)<2:
            print("Wrong Command!!!")
            print("Usage: python3 daycare_test [clear]/[fill]")
            return
        if sys.argv[1] is not None:
            if sys.argv[1] == "fill":
                self.fill_table()
            elif sys.argv[1] == "clear":
                self.clear_table()
            else:
                print("Wrong Command!!!")
                print("Usage: python3 daycare_test [clear]/[fill]")
        else:
            print("Wrong Command!!!")
            print("Usage: python3 daycare_test [clear]/[fill]")

if __name__ == '__main__':
        fill = fill_database()
        fill.start()