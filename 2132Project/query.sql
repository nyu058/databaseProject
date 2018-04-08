--获得物品评分列表(餐厅id=6)
SELECT *
FROM (SELECT itemid, SUM(rating) AS Total_rating, count(userid) AS num, (SUM(rating)/count(userid))AS avge
    FROM ratingitem
    GROUP BY itemid
    order BY itemid)AS tmp1
WHERE itemid IN (SELECT itemid
FROM menuitem
WHERE restaurantid=6)
--获得用户评分列表(用户id=2)
SELECT *
FROM
    (SELECT *
    FROM ratingitem
    WHERE userid=2) AS tmp1
--获得餐厅评分列表(餐厅id=6)
SELECT *
FROM
    (SELECT *
    FROM rating
    WHERE restaurantid=6) AS tmp1

--获得用户6对餐厅评分列表(餐厅id=6)
SELECT *
FROM
    (SELECT *
    FROM rating
    WHERE (restaurantid=6 and userid=6)) AS tmp1
--January 2015没有被评分的餐厅
SELECT distinct name, tel, type, address
FROM restaurant INNER JOIN location ON restaurant.restaurantid=location.restaurantid
WHERE (restaurant.restaurantid not IN 
(SELECT restaurantid
FROM rating
WHERE (date_part('month',Date)=1 and date_part('year',date)=2015))
)
--餐厅低于用户8给的最低staff分
select distinct name, address, firstopen
from
    (
select *
    from
        (select date, location.restaurantid, firstopen, address
        from (select date, restaurantid
            from rating
            where restaurantid in(
SELECT restaurantid
            FROM rating
            WHERE (staff<(SELECT min
            FROM(SELECT userid, min(staff)
                FROM rating
                GROUP BY userid)AS tmp1
            WHERE userid=8)))) as tmp1 left join location on tmp1.restaurantid=location.restaurantid)
as tmp4
        inner join restaurant
        on restaurant.restaurantid=tmp4.restaurantid
    order by date) as tmp5
--Chinese类型餐厅获得的最高评分
select restaurantid, most
into temporary
table tmp
From
(SELECT restaurantid, MAX(food) as most
FROM rating
where restaurantid in (select restaurantid
from restaurant
where type='Chinese')
GROUP BY restaurantid)
as tmp1
    inner join
(SELECT MAX(food)
FROM rating
where restaurantid in (select restaurantid
from restaurant
where type='Chinese'))
as tmp2 on tmp1.most=tmp2.max;
select userid, restaurantid
into temporary
table tmpt2 from rating where
((restaurantid in
(select restaurantid
from tmp)
)and
(food in
(select most
from tmp)
))
;
select tmp2.name, rater.name
from (
select name, tmp1.userid
    from
        (select userid, tmpt2.restaurantid, name
        from tmpt2 inner join restaurant on restaurant.restaurantid=tmpt2.restaurantid)as tmp1 left join rating on ((tmp1.userid=rating.userid)and(tmp1.restaurantid=rating.restaurantid))
    where food=(select most
    from tmp))as tmp2 inner join rater on tmp2.userid=rater.userid
--(须实际测试)
--根据评价数量判断哪个类型餐厅更受欢迎
select *
from
    (select type, count(type)
    from
        (select restaurantid
        from rating)as tmp1 left join restaurant on tmp1.restaurantid=restaurant.restaurantid
    group by type
    order by count desc)as tmp2
where count=(select max(count)
from (select type, count(type)
    from
        (select restaurantid
        from rating)as tmp1 left join restaurant on tmp1.restaurantid=restaurant.restaurantid
    group by type
    order by count desc)as tmp3)
--找出整体给出的评价最高的rater
select tmp5.name as ratername, joindate, reputation, restaurant.name as restaurant_name, date
from
    (select name, tmp4.userid, joindate, reputation, date, restaurantid
    from
        (select name, tmp3.userid, joindate, reputation
        from
            (select userid
            from (select userid, cast((avg(food+mood)/2)as dec(3,2)) as rating
                from rating
                group by userid) as tmp1
            where rating=(select max(rating)
            from (select userid, cast((avg(food+mood)/2)as dec(3,2)) as rating
                from rating
                group by userid) as tmp2)
)as tmp3
            left join rater on tmp3.userid=rater.userid) as tmp4
        left join rating on tmp4.userid=rating.userid) as tmp5
    left join restaurant on tmp5.restaurantid=restaurant.restaurantid
--找出food或mood评分整体最高的rater
select tmp5.name as ratername, joindate, reputation, restaurant.name as restaurant_name, date
from
    (select name, tmp4.userid, joindate, reputation, date, restaurantid
    from
        (select name, tmp3.userid, joindate, reputation
        from
            (select userid
            from (select userid, foodavg as rating
                    from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg
                        from rating
                        group by userid)as tmpp1
                    where foodavg>moodavg
                union
                    select userid, moodavg as rating
                    from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg
                        from rating
                        group by userid)as tmpp2
                    where foodavg<=moodavg)

 as tmp1
            where rating=(select max(rating)
            from (select userid, foodavg as rating
                    from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg
                        from rating
                        group by userid)as tmpp1
                    where foodavg>moodavg
                union
                    select userid, moodavg as rating
                    from (select userid, cast((avg(food))as dec(3,2)) as foodavg, cast((avg(mood))as dec(3,2)) as moodavg
                        from rating
                        group by userid)as tmpp2
                    where foodavg<=moodavg)

 as tmp2)
)as tmp3
            left join rater on tmp3.userid=rater.userid) as tmp4
        left join rating on tmp4.userid=rating.userid) as tmp5
    left join restaurant on tmp5.restaurantid=restaurant.restaurantid
--对餐厅Fraser Cafe最频繁的评价者
select ratername, reputation, name, date, comment, price
from
    (select name as ratername, reputation, date, itemid, comment
    from
        (select tmp3.userid, name, reputation
        from
            (select *
            from (SELECT userid, count(userid)
                FROM rating
                WHERE restaurantid=(select restaurantid
                from restaurant
                where name='Fraser Cafe')
                group by userid)as tmp2
            where count=
(select max(count)
            from
                (SELECT userid, count(userid)
                FROM rating
                WHERE restaurantid=(select restaurantid
                from restaurant
                where name='Fraser Cafe')
                group by userid)as tmp1))as tmp3
            left join rater on tmp3.userid=rater.userid)as tmp4 left join ratingitem on tmp4.userid=ratingitem.userid)as tmp5
    left join menuitem on tmp5.itemid=menuitem.itemid
where restaurantid=(select restaurantid
from restaurant
where name='Fraser Cafe')

--比love666平均评分低的人
select name, email
from
    (select *
    from(select userid, cast(((avg(price)+avg(food)+avg(mood)+avg(staff))/4)as dec(3,2))as avgrate
        from rating
        group by userid)as tmp2
    where avgrate<(select avgrate
    from(select userid, cast(((avg(price)+avg(food)+avg(mood)+avg(staff))/4)as dec(3,2))as avgrate
        from rating
        group by userid)as tmp1
    where userid=(select userid
    from rater
    where name='Love666'))
)as tmp3
    left join rater on tmp3.userid=rater.userid

--(o)找到针对同一家餐厅方差最大的人，列出rate
select userid, cast(((price+food+mood+staff)/4)as dec(3,2))as avgrate, restaurantid
into temporary
table tmp from rating;
select tmp2.userid, tmp2.restaurantid, ((avgrate-avg)*(avgrate-avg)/(count))as variance
into temporary
table tmpp2 from tmp as tmp2 left join
(select userid, restaurantid, avg(avgrate), count(userid)
from tmp
group by userid,restaurantid)
as tmp1 on
(tmp2.userid=tmp1.userid) and
(tmp2.restaurantid=tmp1.restaurantid);

select ratername, tmpa.type, email, name, price, food, mood, staff
from
    (select tmp6.name as ratername, type, email, restid, price, food, mood, staff
    from
        ((select distinct userid, restaurantid as restid
        from tmpp2
        where variance=(select max(variance)
        from tmpp2))as tmp5
        left join rater on rater.userid=tmp5.userid)as tmp6 left join rating on restid=restaurantid
)as tmpa left join restaurant on restid=restaurantid
--（需要实际测试）
