# Kaist_What_Team

# <image src="./app/src/main/res/drawable/travel2.png" width="45"/> 부산 풀코스


## 개발 팀원

- 부산대 19학번 신종민
- KAIST 새내기과정학부 김지나

<br>

## 개발 환경
Client)
- OS: Android (minSdk: 26, targetSdk: 32)
- Language: Java
- IDE: Android Studio
- Target Device: Galaxy S7

Server)
- Stack: 
- Language: 
- Framework
- DataBase: 
- Package: 

<br>

## 앱 소개
<image src="./app/src/main/res/drawable/s0.png" width="200"/> 

'부산 풀코스' 앱은 부산 맛집, 부산 명소, 부산 축제 추천 기능을 갖춘 앱입니다. <br>
부산 맛집, 부산 명소, 부산 축제에서 좋아요를 누른 각 목록을 ‘함 봐라’탭에서 한번에 확인할 수 있습니다.
<br>

### 1. 회원가입, 로그인, 내 정보 수정

<div>

<image src="./app/src/main/res/drawable/s1.png" width="200"/>

<image src="./app/src/main/res/drawable/s2.png" width="200"/>

<image src="./app/src/main/res/drawable/s3.png" width="200"/>

<image src="./app/src/main/res/drawable/s18.png" width="200"/>

</div>

- 처음 앱을 실행하면 Splash 기능과 함께 로그인 탭으로 이동합니다. 계정이 없을 경우, 회원가입 탭으로 이동하여 회원가입을 진행할 수 있습니다.
- 로그인 후, `FirstPage`로 이동하며, `username`+ 어서온나! 멘트와 함께 부산 날씨를 확인할 수 있으며, 부산 맛집, 부산 명소, 부산 축제 버튼을 선택 가능합니다.
- 오른쪽 상단 버튼을 눌러주면 회원 정보 수정이 가능합니다.

<br>

<br>

<div>

<image src="./app/src/main/res/drawable/s4.png" width="200"/>

</div>

### 2. 뭐 물래? (맛집 추천)

<div>

<image src="./app/src/main/res/drawable/s5.png" width="200"/>
<image src="./app/src/main/res/drawable/s6.png" width="200"/>
<image src="./app/src/main/res/drawable/s7.png" width="200"/>
<image src="./app/src/main/res/drawable/s8.png" width="200"/>
<image src="./app/src/main/res/drawable/s81.png" width="200"/>

- **뭐 물래?** 버튼을 클릭하면 `BusanFoodStep1`으로 이동합니다.
- 키워드를 선택하면 선택한 키워드에 맞게 부산 맛집 정보를 ListView로 띄워줍니다. 이때 키워드는 최소 1개부터 최대 3개까지 선택 가능합니다.
    - **태그 작업**
    
    태그 클릭 시에 addTag라는 메서드를 호출해서 DB에 tag들을 JSON으로 보내고 tag에 해당하는 데이터를 JSON으로 받아서 ListView를 이용하여 구현하였습니다.
    
    ```sql
    app.post('/getFoodByTags', (req, res) => {
        console.log("getFoodByTags 요청 시작 !!!!");
        console.log(req.body);
        var jsonArraylist = new Array();
        var jsonArray = {
            body: ''
        };
        var sql = `select * from food_mst where tag1 like ` + connection.escape('%' + req.body.tag1 + '%');
        if (req.body.count == 0) {
            console.log("태그 0개");
            var sql = `select * from food_mst where id = 0;`;
        } else if (req.body.count == 1) {
            console.log("태그 1개");
            var sql = `select * from food_mst where tag1 like ` + connection.escape('%' + req.body.tag1 + '%') + `;`;
        }
        else if (req.body.count == 2) {
            console.log("태그 2개");
            var sql = `select * from food_mst where tag1 like ` + connection.escape('%' + req.body.tag1 + '%') + ` and tag1 like ` + connection.escape('%' + req.body.tag2 + '%') + `;`;
        }
        else if (req.body.count == 3) {
            console.log("태그 3개");
            var sql = `select * from food_mst where tag1 like ` + connection.escape('%' + req.body.tag1 + '%') + ` and tag1 like ` + connection.escape('%' + req.body.tag2 + '%') + ` and tag1 like ` + connection.escape('%' + req.body.tag3 + '%') + `;`;
        }
    
        //var sql = ;
        var values = [req.body.tag1, req.body.tag2, req.body.tag3];
        connection.query(sql, values, (error, rows) => {
            if (error) {
                throw error;
    
            }
            console.log('select 성공 !!!!!!!!');
            if (rows.length == 0) {
                console.log('Json object : ', json_object_send);
                res.send(json_object_send);
                return;
            }
            console.log("가져온거 갯수" + rows.length);
            for (let i = 0; i < rows.length; i++) {
                console.log(rows[i]["tel"]);
                var json_object_send = {
                    id: rows[i]["id"],
                    mainTitle: rows[i]["main_title"],
                    place: rows[i]["place"],
                    subTitle: rows[i]["sub_title"],
                    img: rows[i]["img"],
                    context: rows[i]["context"],
                    tag1: rows[i]["tag1"],
                    latitude: rows[i]["latitude"],
                    longitude: rows[i]["longitude"],
                    tag2: '',
                    tag3: '',
                    tel: rows[i]["tel"]
                };
                jsonArraylist.push(json_object_send);
            }
    
            setTimeout(() => {
                var strJson = JSON.stringify(jsonArraylist);
                jsonArray.body = strJson;
                res.send(jsonArray);
            }, 200);
    
        });
    });
    ```
    
    - **태그** 3개로 음식 정보 받아오기
    
    ```sql
    app.post('/likeFestivalList', (req, res) => {
        console.log("likeFestivalList 요청 시작 !!!!");
        console.log(req.body);
        var likeList = new Array();
        var jsonArraylist = new Array();
        var jsonArray = {
            body: ''
        };
        var sql = `select festival from festival_like_mst where user_id = ?`;
        var values = [req.body.userId];
        connection.query(sql, values, (error, rows) => {
            if (error) {
                throw error;
    
            }
            console.log('select 성공 !!!!!!!!');
            for (let i = 0; i < rows.length; i++) {
                var jsontemp = {
                    festival_id: rows[i]["festival"] + ""
                }
    
                likeList.push(jsontemp);
                console.log(rows[i]["festival"]);
            }
    
        });
        setTimeout(() => {
            var strJson = JSON.stringify(likeList);
            jsonArray.body = strJson;
            console.log(jsonArray);
            res.send(jsonArray);
        }, 200);
    });
    ```
    
- 리스트로 나타난 항목 중 하나를 선택하면 `FoodFullImage`로 이동하며, 다음의 정보들을 확인할 수 있습니다.
    - **BusanFoodDto**
    
    ```jsx
    package com.example.whatmain;
    
    import java.io.Serializable;
    
    public class BusanFoodDto implements Serializable {
        int id;
        String mainTitle ;
        String place;
        String subTitle;
        String img;
        String context;
        String tag1;
        String tag2;
        String tag3;
        double latitude;
        double longitude;
        //전화 string 추가
        String call;
        public int getId() {
            return id;
        }
    
        public void setId(int id) {
            this.id = id;
        }
    
        public String getMainTitle() {
            return mainTitle;
        }
        public void setMainTitle(String mainTitle) {
            this.mainTitle = mainTitle;
        }
        public String getPlace() {
            return place;
        }
        public void setPlace(String place) {
            this.place = place;
        }
        public String getSubTitle() {
            return subTitle;
        }
        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }
        public String getImg() {
            return img;
        }
        public void setImg(String img) {
            this.img = img;
        }
        public String getContext() {
            return context;
        }
        public void setContext(String context) {
            this.context = context;
        }
        public String getTag1() {
            return tag1;
        }
        public void setTag1(String tag1) {
            this.tag1 = tag1;
        }
        public String getTag2() {
            return tag2;
        }
        public void setTag2(String tag2) {
            this.tag2 = tag2;
        }
        public String getTag3() {
            return tag3;
        }
        public void setTag3(String tag3) {
            this.tag3 = tag3;
        }
        public double getLongitude() {
            return longitude;
        }
        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
        public double getLatitude() {
            return latitude;
        }
        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
    
        //call 추가
        public String getCall(){return call;}
        public void setCall(String call){this.call=call;}
    
    }
    ```
    
- `FoodFullImage`에서 좋아요 버튼, 전화 걸기 버튼, 공유하기 버튼을 사용할 수 있습니다.
    - **좋아요** 버튼을 클릭하면 좋아요 리스트에 추가되며, 취소도 가능합니다.
        - **좋아요** 추가
        
        ```sql
        app.post('/likeFestivalHeart', (req, res) => {
            console.log("likeFestivalHeart 요청 시작 !!!!");
            console.log(req.body);
            var json_object_send = {
                code: ''
            };
        
            var sql = `insert into festival_like_mst values(?,?);`;
            var values = [req.body.userId, req.body.festivalId];
            console.log(req.body.userId);
            connection.query(sql, values, (error, rows) => {
                if (error) {
                    json_object_send.code = '0';
                    throw (error);
                }
                json_object_send.code = '1';
            });
            json_object_send.code = '1';
            console.log('Json object : ', json_object_send);
            res.send(json_object_send);
        });
        ```
        
        - **좋아요** 취소
        
        ```sql
        app.post('/dislikeFestivalHeart', (req, res) => {
            console.log("dislikeFestivalHeart 요청 시작 !!!!");
            console.log(req.body);
            var json_object_send = {
                code: ''
            };
            var sql = `delete from festival_like_mst where user_id = ? and festival = ?;`;
            var values = [req.body.userId, req.body.festivalId];
            console.log(req.body.userId);
            connection.query(sql, values, (error, rows) => {
                if (error) {
                    json_object_send.code = 0;
                    throw (error);
                }
                json_object_send.code = 1;
            });
            json_object_send.code = 1;
            console.log('Json object : ', json_object_send);
            res.send(json_object_send);
        });
        ```
        
    - **전화 걸기** 버튼을 클릭하면 식당으로 전화를 걸 수 있습니다.
    - **공유하기** 버튼을 클릭하면 해당 식당 네이버 검색 링크를 친구에게 공유할 수 있습니다.
- latitude와 longtitude를 받아와서 Google Map으로 위치도 확인할 수 있게끔 구현했습니다.
- **더 많은 장소 더보기** 버튼을 클릭하면 다시 `BusanFoodStep1`로 이동하면서 리스트를 다시 확인할 수 있습니다.

<br>

<br>

### 3. 어디 갈래? (부산 명소 추천)

<div>

<image src="./app/src/main/res/drawable/s9.png" width="200"/>
<image src="./app/src/main/res/drawable/s10.png" width="200"/>
<image src="./app/src/main/res/drawable/s11.png" width="200"/>

</div>

- **어디 갈래 항목은** 전화 기능을 제외한 나머지 기능들은 **뭐 물래** 기능과 동일하게 구현되었습니다.

<br>

<br>

### 4. 뭐 할래? (부산 축제 추천)

<div>

<image src="./app/src/main/res/drawable/s12.png" width="200"/>
<image src="./app/src/main/res/drawable/s13.png" width="200"/>

</div>

- **뭐 할래** 항목은 태그 기능 없이 모든 부산 축제 정보들을 ListView로 보여줍니다.
- **뭐 할래** 항목은 URL을 통해 홈페이지로 이동이 가능합니다.

### 5. 함 봐라 (좋아요 리스트)

<div>

<image src="./app/src/main/res/drawable/s14.png" width="200"/>

<image src="./app/src/main/res/drawable/s15.png" width="200"/>
<image src="./app/src/main/res/drawable/s16.png" width="200"/>
<image src="./app/src/main/res/drawable/s17.png" width="200"/>

</div>

- 함 봐라 버튼을 누르면, 뭐 물래, 어디 갈래, 뭐 할래 항목에서 좋아요를 누른 리스트를 ListView로 띄워줍니다. 리스트의 각 항목을 클릭하면 `FullImage` Activity로 이동합니다.

* * *
### 공공 데이터 활용
<image src="./app/src/main/res/drawable/sdata.png" width="200"/>
<image src="./app/src/main/res/drawable/sdata2.png" width="200"/>
<br>

<br>
위의 공공 데이터를 활용하여 위와 같이 `BusanFoodDto`, `BusanFestivalDto`, `BusanTodoDto` 각각을 구성했습니다.
