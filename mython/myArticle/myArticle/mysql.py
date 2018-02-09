# -*- coding: utf-8 -*-

import MySQLdb
import sys
reload(sys)
sys.setdefaultencoding("utf-8")

config = {
    'host': 'localhost',
    'port': 3306,
    'user': 'root',
    'passwd': '123456',
    'db': 'test',
    'charset': 'utf8',
    #'allowMultiQueries': 'true'
}
class Mysql(object) :
    def connect(self):
        # 打开数据库连接
        db = MySQLdb.connect(**config)
        return db

    #获取数据库版本号
    def getVersion(self):
        db = self.connect()
        cursor = db.cursor()
        # 使用execute方法执行SQL语句
        cursor.execute("SELECT VERSION()")

        # 使用 fetchone() 方法获取一条数据
        data = cursor.fetchone()

        print "Database version : %s " % data
        # 关闭数据库连接
        db.close()

    #插入数据
    def insert(self , sql , lists):
        db = self.connect()
        cursor = db.cursor()
        try:
            # 执行sql语句
            cursor.execute(sql,lists)
            #cursor.execute("insert into xiaoshuo_about(parent_id,title,`dec`,`count`,author,status,sort) values(%s,%s,%s,%s,%s,%s,%s)")
            # 提交到数据库执行
            db.commit()
        except:
            # Rollback in case there is any error
            db.rollback()
        # 关闭数据库连接
        db.close()
        return cursor

    #小说信息
    def xiaoshuoAbout(self,val):
        db = self.connect()
        cursor = db.cursor()
        # 打开数据库连接
        sql = "insert into xiaoshuo_about(parent_id,title,`dec`,`count`,author,status,sort) values(%s,%s,%s,%s,%s,%s,%s)"
        try:
            cursor.execute(sql,val)
        except:
            print("mysql失败了:"+''.join(val))
            db.rollback()
        finally:
            db.commit()
            db.close()
        if cursor is None:
            print(1111)

        return cursor

    #插入章节要 例如 该书的第一章，第二章
    def xiaoshuoList(self,val):
        db = self.connect()
        cursor = db.cursor()
        # 打开数据库连接
        sql = "insert into xiaoshuo_chapter(parent_id,title,`num`) values(%s,%s,%s)"
        try:
            cursor.execute(sql, val)
        except:
            print("mysql失败了:" + ''.join(val))
            db.rollback()
        finally:
            db.commit()
            db.close()
        return cursor