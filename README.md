## 백엔드 설정

### 현재 사용 브랜치
origin master

- git pull origin master

- git add .
- git commit -m "example"
- git push origin master

### EC2 & RDS 설정
- ssh -f -i (키 주소).pem ubuntu@(ec2 IP주소) -L 3307:(rds 엔드포인트):3306 -N
- ps aux | grep ssh -> pid 확인
- kill pid -> pid 종료

- spring.datasource.url=jdbc:mysql://localhost:3307/CHARKAK?serverTimezone=Asia/Seoul 수정 필요
