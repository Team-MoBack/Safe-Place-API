## 프로젝트 일지

### MySQL vs PostgreSQL 중 MySQL을 선택한이유?

1. Google과 같은 유명 회사들이 채택한 신뢰성있는 DB
2. 1분에 한번씩 가게에 있는 사람수를 보내는 OLTP연산을 수행하는데, OLTP 성능의 경우 MySQL이 성능이 좋음
3. MySQL은 Geospatial Index기능이 내장 되어있으므로 구현이 편리(PostgreSQL의 경우 PostGIS extention 사용해야함)

## 왜 Random Token 방식으로 인증했나요?

Stateful Session Cookie 방식의 경우, 브라우저에서 cookie에 session id가 저장되던 방식이 적용되지 않으므로 사용하기 어렵다고 판단해서 token based 인증을 선택하게 되었다. JWT와 random token를 비교하면, JWT는 서버에 secret값이 유출되면 누군가 토큰을 임의로 생성할 수 있고, refresh token이 유출될 경우 누군가 임의로 토큰을 새로 발급할 수 있다. 그래서 refresh token 또한 기간을 짧게두어야 하는데, 그러한 메커니즘을 구현하기 위해서 Redis와 같은 휘발성 DB가 필요할 것으로 예상된다. 즉 stateless 하다는 장점이 사라지게 된다. 반면 Random token 방식을 사용하게 되면 token을 누군가 임의로 생성할 수는 없고 token이 유출될것을  대비하여 Redis에 저장하여 주기적으로 삭제가 되도록 한다. 클라이언트는 token이 만기될 경우, 서버에게 다시 token을 요청해서 로그인이 풀리지 않도록 할수 있다. 그렇게 되면 attacker는 토큰이 주기적으로 바뀌게 되므로 공격하기가 어려워질 것이다.