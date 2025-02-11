#!/usr/bin/env python

import os
from urllib import parse

HEADER="""# 
# 백준 & 프로그래머스 문제 풀이 목록

프로그래머스의 경우, 푼 문제 목록에 대한 마이그레이션이 필요합니다.

"""

def main():
    content = ""
    content += HEADER
    
    directories = [];
    solveds = [];

    for root, dirs, files in os.walk("."):
        dirs.sort()
        if root == '.':
            for dir in ('.git', '.github'):
                try:
                    dirs.remove(dir)
                except ValueError:
                    pass
            continue

        category = os.path.basename(root)
        
        if category == 'images':
            continue
        
        directory = os.path.basename(os.path.dirname(root))
        
        if directory == '.':
            continue
            
        if directory not in directories:
            if directory in ["백준", "프로그래머스"]:
                content += "## 📚 {}\n".format(directory)
            else:
                # directory에 해당하는 폴더의 경로를 구합니다.
                directory_path = os.path.join(".", directory)
                # 해당 폴더 내의 하위 폴더 목록을 구합니다.
                # 예시에서는 'images' 폴더는 제외하도록 했습니다.
                subfolders = [
                    d for d in os.listdir(directory_path)
                    if os.path.isdir(os.path.join(directory_path, d)) and d != 'images'
                ]
                # 하위 폴더 개수를 헤더에 추가합니다.
                content += "### 🚀 {} ({}개)\n".format(directory, len(subfolders))
                content += "| 문제번호 | 링크 |\n"
                content += "| ----- | ----- |\n"
            directories.append(directory)

        for file in files:
            if category not in solveds:
                content += "|{}|[링크]({})|\n".format(category, parse.quote(os.path.join(root, file)))
                solveds.append(category)
                print("category : " + category)

    with open("README.md", "w") as fd:
        fd.write(content)
        
if __name__ == "__main__":
    main()
