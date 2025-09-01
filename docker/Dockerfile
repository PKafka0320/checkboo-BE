# Java 21 기반 이미지
FROM eclipse-temurin:21-jdk-jammy

# 앱 JAR 복사
COPY ./checkboo-0.0.1-SNAPSHOT.jar /app/app.jar

# 앱 실행
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar", "/app/app.jar"]