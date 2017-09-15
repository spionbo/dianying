from flask import Flask, request, render_template
from scrapy import cmdline

app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
def home():
    cmdline.execute('scrapy crawl baidu --nolog'.split())
    return render_template('home.html')

if __name__ == '__main__':
    app.run()