# LeetCode Slack Bot (beta)

## LeetCode Slack Bot
- LeetCode Bot(beta)은 LeetCode라는 알고리즘 사이트에서 제공해주는 알고리즘 문제들을 랜덤으로 추출하여 Slack 채널에 게시해주는 bot을 말한다


## Release

### v0.0.1
- [x] LeetCode 문제를 가져와 저장할 수 있다
- [x] Slack에 문제를 게시할 시각을 설정할 수 있다

  - 현재는 application.yaml 설정 가능

- [x] 게시할 알고리즘 문제 수를 설정할 수 있다
 
  - 현재는 application.yaml 설정 가능

- [x] 게시할 알고리즘 문제의 난이도 별 문제 수를 설정할 수 있다

  - 현재는 application.yaml 설정 가능

- [x] 문제는 중복되선 안된다

  - beta 버전은 메모리 맵에 문제를 저장하고 있으며, 슬랙에 게시 하게되면 solved 체크를 한다dkw
