# air-quality-service

서울시, 부산시의 실시간 대기환경을 조회하는 API서비스

- retrofit2를 이용한 공공데이터 API 기반 서비스
- Factory Method Pattern 적용으로 앞으로 다른 시/도가 추가될 때 적은 수정으로 기능 확장 가능
- Spring Cache를 통해 local cache로 캐싱 기능 구현(매 정각에 API호출 후 캐시에 저장, 그 이후의 조회는 캐싱된 데이터로 응답)

[api 문서](https://gray-oviraptor-aac.notion.site/API-1fbf83ee380e4a8eadeec7db3b132819)

사용된 공공 데이터
[서울](http://data.seoul.go.kr/dataList/OA-2219/S/1/datasetView.do)
[부산](https://www.data.go.kr/data/15057173/openapi.do)
