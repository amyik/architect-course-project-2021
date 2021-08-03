# 2020-2021 Architect과정 개인과제

## Notice

## Local 개발환경 설정
1. docker-desktop설치
    - local에 docker-desktop 설치 : https://devops.sdsdev.co.kr/confluence/pages/viewpage.action?pageId=261553527
    - wsl2에 kubectl 자동완성 설정 : https://kubernetes.io/ko/docs/reference/kubectl/cheatsheet/
    - (참고) local kubectl로 gcp gke 사용하기 : https://cloud.google.com/sdk/gcloud/reference/container/clusters/get-credentials


2. docker-dsktop에 ingress-controller 설치
    ```bash
    # 사내에서만 : C:\Users\사용자홈\.docker\daemon.json 에 허용 image repo 추가
    # 설정 후 docker-desktop 재시작 필요
    {
    .
    생략
    .
    "insecure-registries": ["storage.googleapis.com", "k8s.gcr.io"],
    .
    생략
    .
    }
    ```   

    ``` bash
    $ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.41.2/deploy/static/provider/cloud/deploy.yaml
    ```

3. google cloud sdk 설치 ( 사내에서 설치 안되는 듯 )
    - https://cloud.google.com/sdk/docs/install?hl=ko
    - gcloud sdk설치 시 sakffold 자동 설치되는 듯
    - 사외에선 intellij cloud code plugin 설치 시 gcloud sdk 자동 설치 지원하는 듯 


4. skaffold 설치 ( 사내에서만 )
    - windows : 소스코드에 포함된 실행파일 'skaffold.exe' 이용 가능
    - 그외 : skaffold설치 ( https://skaffold.dev/docs/install/ )
    

5. 윈도우 환경변수 설정 ( 사내에서만 )
    ```bash
    no_proxy = 127.0.0.1,localhost,70.*,redii.net,*.redii.net,sds.*,192.168.*,code.sds*,10.224.*,*.docker.internal
    http_proxy = http://70.10.15.10:8080
    https_proxy = http://70.10.15.10:8080
    ```

4. gradle Proxy ( 사내에서만 )
    ``` bash
    # ~/.gradle/gradle.properties 을 아래와 같이 작성 후 ide에서 해당 properties파일 사용하는지 확인
    systemProp.proxySet=true
    systemProp.http.proxyHost=70.10.15.10
    systemProp.http.proxyPort=8080
    systemProp.http.nonProxyHosts=70.121.224.52
    systemProp.https.proxyHost=70.10.15.10
    systemProp.https.proxyPort=8080
    systemProp.https.nonProxyHosts=70.121.224.52
    ```
5. Docker Image pull Proxy ( 사내에서만 )
    ``` bash
    # 해결방법 찾는중
    # 사내, 새외에서 사용하는 dockerfile을 동일하게 유지하는 방법이 있을까
    # 사내 전용 skaffold.yaml과 사내 전용 dockerfile 구성하는 법 찾아보기
    # https://skaffold.dev/docs/references/yaml/?version=v2beta13
    ```

6. Docker Build npm Proxy ( 사내에서만 )
    ``` bash
    # 해결방법 찾는중
    # 사내, 새외에서 사용하는 dockerfile을 동일하게 유지하는 방법이 있을까
    ```

## 실행하기
- cmd 에서 실행하기 (로컬에서 skaffold 실행시 안될경우 kubectl config get-contexts로 current k8s 정보 얻어온후 제대로된 환경으로 설정 필요)
  ``` bash
  # 사외에선
  
  $ skaffold dev
  # docker-desktop의 경우 별도 터미널에서 port-forward 필요
  $ kubectl port-forward deployment.apps/ingress-nginx-controller 8080:80 -n ingress-nginx
  
  # 브라우저로 localhost:80 접속
  ```
  ``` bash
  # 사내에선
  
  $ skaffold dev -f skaffold_eshop_proxy.yaml
  or
  $ skaffold dev -f skaffold_proxy.yaml
  # docker-desktop의 경우 별도 터미널에서 port-forward 필요
  $ kubectl port-forward deployment.apps/ingress-nginx-controller 8080:80 -n ingress-nginx
  
  # 브라우저로 localhost:80 접속
  ```

- IntelliJ 에서 실행하기
    - 플러그인 'cloud code' 설치
        - 사내에선 : IDE Settings > Cloude code > Dependencies
          - skaffold 위치 지정 : 소스코드의 skaffold.exe
          - kubectl 위치 지정 : cmd에서 아래 명령어로 kubectl 위치 확인
            ```bash
            $ where kubectl
            ```
        - 사외에선 : IDE Settings > Cloude code > Dependencies : gcloud sdk 자동 설정 이용 
    - Run Configuration Edit > Cloud code : kubernetes 추가
        - 사내에선 : build/deploy > skaffold configuration 파일 지정 : skaffold_proxy.yaml


- Eclipse 에서 실행하기
    - 확인중...
