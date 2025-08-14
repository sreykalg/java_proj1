# java_proj1
- Leng Sreyka
    - Team Leader
    - Tasks
        - create GitHub repository
        - create project
        - create domain
        - create repository
        - create service/ , service/dto, service/mapper
        - Implement api/controller for Team, Member, Lecture, Registration
        - create test cases for repository, service
        - Create table creation scripts
        - Fix bugs
        - Check & Merge Pull Request
- Tan Bunhourt
    - Team Member
    - Tasks
        - Check Materials and Find referencing Documentations
        - Create Domain
        - Create and Handle created controllers (for UI) : Implement controller for Homepage, Team, Member, Lecture, Registration
        - Create ERD
        - Execute Sql query
        - Create templates/ (for UI)
        - Testing
        - Review Flows & Recheck
        - Report bugs

ERD Diagram : 

![ERD](https://github.com/user-attachments/assets/ac4d6eb3-424d-4fcf-86f9-ecd81bbea7c6)

Table Create File : (files)
CREATE TABLE team (
    teamId BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    createdDate DATE
);

CREATE TABLE member (
    memberId BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT,
    address VARCHAR(255),
    createdDate DATE,
    teamId BIGINT,
    FOREIGN KEY (teamId) REFERENCES team(teamId)
);

CREATE TABLE lecture (
    lectureId BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    createdDate DATE
);

CREATE TABLE registration (
    registrationId BIGINT AUTO_INCREMENT PRIMARY KEY,
    registeredDate DATE,
    memberId BIGINT,
    lectureId BIGINT,
    FOREIGN KEY (memberId) REFERENCES member(memberId),
    FOREIGN KEY (lectureId) REFERENCES lecture(lectureId)
);

---

## java_proj1 Setting

- Project : Gradle-Groovy
- Language : Java
- Spring Boot : 3.5.4
- Project Metadata
    - group : com.example
    - artifact : java_proj1
    - name : java_proj1
    - Description : Demo project for Spring Boot
    - package name:  com.example.java_proj1
    - Packaging : Jar
    - Java : 21
- Dependencies
    - Spring Web
    - Spring Data JPA
    - H2 Database
    - Validation
    - Thyleamf
    - lombok



Notion link for backup Readme: 
https://www.notion.so/Java-Project-1-23d58623f85d80388c19f8c671ce56b8?source=copy_link
