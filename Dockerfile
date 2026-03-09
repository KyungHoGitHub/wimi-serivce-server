# 1단계: 빌드 스테이지 (Gradle 9 버전으로 업그레이드)
FROM gradle:jdk17 AS build
WORKDIR /app

# 모든 프로젝트 파일을 복사
COPY . .

# 빌드 실행 (최신 버전에서는 bootJar가 문제없이 작동할 겁니다)
RUN gradle bootJar -x test

# 2단계: 실행 스테이지
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]