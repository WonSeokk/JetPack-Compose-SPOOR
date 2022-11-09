# JetPack-Compose-SPOOR
SPOOR는 JetPack Compose를 사용한 유기동물 검색 어플입니다.  
[농림축산식품부]의 [정부 공공 데이터] API를 사용하여 데이터 정보를 가져옵니다.

## :hammer::wrench:Building the project
어플 빌드 후 정상적인 실행을 위하여 [정부 공공 데이터] API Key를 발급 받아야 합니다.  
무료로 Key를 발급 받은 후 `local.properties`에 다음과 같이 Key를 추가합니다.  
`api.key=발급 키`

## Screenshots
<img src="screenshots/spoor.gif"/>

## Different components used in the project
 - [MVI Orbit][orbit] MVI(Model-View-Intent) 아키텍쳐를 사용
 - [ViewModel][viewmodel] ViewModel 패턴 적용
 - [Jetpack Compose][compose] Jetpack Compose 활용 UI개발
 - [Google Accompanist][accompanist] 구글 UI 기능
 - [Collapsing ToolBar][toolbar] Collapsing ToolBar
 - [Compose navigation][navigation] Navigaion 활용 Compose 화면 관리
 - [Hilt][hilt] 의존성 주입
 - [Coroutines][coroutines] 비동기 작업 처리
 - [Retrofit][retrofit] API 네트워크 호출
 - [Paging 3][paging] 페이징 처리 
 - [Coil][coil] 이미지 처리
 - [Room][room] 관심 목록
 - [Calendar][calendar] Sample Code 참고하여 구현
 - [Material Icons][icons] 아이콘 적용
 - [Material Icons][icons] 아이콘 적용

## Features to be developed
- 보호소 위치 지도 표시(보호소 좌표 API / Map API 활용) 
- 공유 기능
- 다크 모드 UI

[농림축산식품부]: https://www.animal.go.kr/front/index.do
[정부 공공 데이터]: https://www.data.go.kr/data/15098931/openapi.do
[orbit]: https://github.com/orbit-mvi/orbit-mvi
[compose]: https://developer.android.com/jetpack/compose?gclid=CjwKCAjwyIKJBhBPEiwAu7zll9bjLDRqSH7XtNL-G0txRAeT_QLCcws-_VYPI9Ea-cxFzEC69YbslxoC6BEQAvD_BwE&gclsrc=aw.ds
[navigation]: https://developer.android.com/jetpack/compose/navigation
[hilt]: https://developer.android.com/training/dependency-injection/hilt-android
[viewmodel]: https://developer.android.com/topic/libraries/architecture/viewmodel
[paging]: https://developer.android.com/jetpack/compose/lists#large-datasets
[coil]: https://coil-kt.github.io/coil/compose/
[retrofit]: https://square.github.io/retrofit/
[coroutines]: https://developer.android.com/kotlin/coroutines
[icons]: https://fonts.google.com/icons?selected=Material+Icons
[accompanist]: https://github.com/google/accompanist
[toolbar]: https://github.com/onebone/compose-collapsing-toolbar
[calendar]: https://github.com/android/compose-samples/tree/main/Crane
[room]: https://developer.android.com/jetpack/androidx/releases/room
