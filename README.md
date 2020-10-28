# Safe Place : Make your local society better
[![Build Status](https://travis-ci.com/Team-MoBack/Safe-Place-API.svg?branch=main)](https://travis-ci.com/Team-MoBack/Safe-Place-API)  

*2020 Junction x Seoul - Team 'Moback'*  

<img src=./images/junction.png width="300">  


## About
------------------
* __Junction x Seoul :__ https://app.hackjunction.com/events/junctionx-seoul
* 트랙3 라쿠텐 API 3등 프로젝트입니다.
> It is Track 3 Rakuten API 3rd place project.
<img src=./images/Desktop-1.png width="400">
  
<img src=./images/track_winner.png width="400">

## Overview
---------------
### 1. What Problem Solving?
우리 팀은 코로나-19로 인해 사람들이 예전처럼 자유롭게 외출하지 못한다는 점에 주목하였습니다. 사람들은 근처의 슈퍼마켓을 가더라도 좀 더 안전한 곳을 방문하길 원합니다.  
그러므로, 우리 팀은 그들의 현재 위치를 기반으로 근처의 위생적으로 안전한 가게들을 소개해주는 앱을 기획하였습니다.  
  
>Our team noted that COVID-19 prevents people from going out as freely as before. People want to visit a safer place even if they go to a nearby supermarket.  
>Therefore, our team planned an app that introduces the nearby quarantine safety shops based on their current location.  
  
### 2. App Introduction
코로나 시대를 살아가며 안전한 외출을 하길 원하시나요?  
이 앱은 당신의 현재 위치를 기준으로 주변의 안전하고 깨끗한 가게들을 소개시켜줍니다.  
그리고 당신은 직원들이 청결한지, 고객들이 마스크를 쓰고 있는지, 위생 용품들이 배치되어있는지 등을 확인하고 평가할 수 있습니다.  
  
>Living in COVID Era, do you want a safe outing?  
>This app introduces you to several of the cleanest and safest places to visit based on your current location.  
>You can check or evaluate whether employees are clean, customers are wearing masks, and quarantine supplies are deployed.

### 3. Team Member
* __Android Developer :__ `Jun Hyeon Park` (https://github.com/Sky-Titan), `JoSuah` (https://github.com/surzioarmani)
* __Back-End Developer :__ `Jaegu Kim` (https://github.com/JaeguKim)
* __Designer :__ `Ye Ajeong`
* __Plannar :__ `Tak`

## Functions
--------------
1. 자영업자들은 그들의 가게를 앱에 등록할 수 있습니다.
2. 그리고 자신의 가게가 현재 고객 수가 적고, 깨끗하다는 것을 사진으로 증명하여, 앱 이용자들에게 홍보할 수 있습니다.
3. 일반 앱 이용자들은 홍보글을 보고 그들이 외출할 때 깨끗한 가게로 향할 수 있습니다.
4. 만약 가게를 방문했는 데, 사실과 다르다면 해당 가게의 별점과 리뷰를 남길 수 있습니다.
5. 그리고 앱을 더 많이 사용하고 리뷰를 더 많이 남길수록, 당신은 더 많은 포인트를 얻게 되고 이것을 다른 곳에 기부할 수 있습니다.
6. 또한 당신의 지역 혹은 타지역의 이용자들과 포인트 랭킹을 비교할 수 있습니다.  

>1. Self-employed people can register their stores with the app.
>2. And with photos, you can prove that your store has fewer customers and is clean and promote it to app users.
>3. Regular app users can view the promotion and head to a clean store if they go out.
>4. If you visit the store, you can evaluate it and leave a review if it's not true.
>5. And the more you use the app and leave a review, the more points you get, and you can donate them elsewhere.
>6. And you can compare points rankings with people in your area or other areas.  

## How To Run?  
1. Download docker-compose.yml in this [link](https://gist.github.com/JaeguKim/0ef42b6c8c6af4612daf9c3f315d491c)
2. Unzip it, move to directory of docker-compose.yml
3. Run  ``` docker-compose up ```

## ER Diagram  
will be updated soon  

## API Documentation
[Swagger Hub Link](https://app.swaggerhub.com/apis-docs/JaeguKim/safe-place_rest_api/0.0.0#/)  

## Version of tools  
* Spring Boot - 2.3.4.RELEASE
* Spring Security
* Swagger2 - 2.9.2
* MySQL - 5.1.45
* JUnit5
* JAVA - 11

