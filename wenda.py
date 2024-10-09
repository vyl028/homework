import requests
from bs4 import BeautifulSoup
from requests import session
import diskcache as dc
from rich.console import Console
from rich.markdown import Markdown


class YiYan(object):
    def __init__(self, appid, ak, sk):
        self.appid = appid
        self.ak = ak
        self.sk = sk
        self.s = session()
        headers = {
            'Content-Type': 'application/json'
        }
        self.s.headers.update(headers)
        self.cache = dc.Cache('./cache')

    def get_access_token(self):
        key = 'access_token'
        if not (access_token := self.cache.get(key)):
            response = self.s.post(
                f'https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id={self.ak}&client_secret={self.sk}',
                json='""')
            access_token = response.json()['access_token']
            self.cache.set(key, access_token, expire=60 * 60 * 24 * 1)
        return access_token

    def generate_answer_from_content(self, content, question):
        """
        基于提取的文档内容进行问答，确保内容长度不超出限制
        """
        access_token = self.get_access_token()
        url = f"https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/eb-instant?access_token={access_token}"
        
        # 文档内容摘要处理
        summarized_content = self.summarize_content(content)
        
        data = {
            "messages": [
                {"role": "user", "content": f"根据以下内容回答问题：\n{summarized_content}\n\n问题：{question}"}
            ]
        }
        
        response = self.s.post(url, json=data)
        result = response.json().get('result', '无法生成回答')
        self.print_markdown(result, question)
        return result

    def summarize_content(self, content):
        """
        对文档内容进行摘要处理，提取关键信息
        """
        # 简单处理：提取文档的前几段作为摘要
        sentences = content.split('\n')
        summary = '\n'.join(sentences[:5])  # 取前5段
        return summary

    def print_markdown(self, content, question):
        markdown_content = Markdown(f"### Q: {question}\n\n---\n\n{content}")
        console = Console()
        console.print(markdown_content)

    def extract_content_from_url(self, url):
        """
        提取给定URL的文档内容
        """
        try:
            response = requests.get(url)
            soup = BeautifulSoup(response.content, 'html.parser')
            
            # 尝试提取<p>标签内容
            paragraphs = soup.find_all('p')
            if not paragraphs:
                # 如果没有<p>标签内容，尝试获取其他标签中的文本，比如<div>、<span>等
                divs = soup.find_all('div')
                paragraphs = [div.get_text() for div in divs if div.get_text().strip()]
            
            content = '\n'.join([p.get_text() for p in paragraphs if p.get_text().strip()])
            
            # 如果内容仍为空，打印网页结构以调试
            if not content:
                print("未提取到有效的文档内容")
                print(soup.prettify())  # 打印整个网页结构，帮助调试
                return "无法提取文档内容"
            
            return content
        except Exception as e:
            return f"提取文档内容时发生错误: {str(e)}"


def main():
    appid = '59073489'  # 你自己的应用ID
    ak = 'skOQK8iKZylRWRFxcWKWVRZE'  # 你自己的API Key
    sk = 'ua31AWV0SVv4SbwZCiyfffp9aBWlnlkI'  # 你自己的Secret Key
    app = YiYan(appid, ak, sk)

    # 文档URL
    doc_url = "https://www.gov.cn/jrzg/2011-10/25/content_1978202.htm"
    # 提取文档内容
    doc_content = app.extract_content_from_url(doc_url)
    
    # 确认文档内容是否提取成功
    if "提取文档内容时发生错误" in doc_content or "无法提取文档内容" in doc_content:
        print(f"文档提取失败：{doc_content}")
        return
    
    # print(f"提取的文档内容（前500字符）：{doc_content[:500]}")  # 只打印前500个字符以供调试
    
    # 启动问答循环
    while True:
        question = input("请输入问题（输入 'exit' 退出）：")
        if question.lower() == 'exit':
            break
        # 基于提取的内容回答问题
        app.generate_answer_from_content(doc_content, question)


if __name__ == '__main__':
    main()
