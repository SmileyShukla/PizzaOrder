# PizzaOrder
Aquent Coding Challenge


Resources Used:
 	Mac Terminal
	Eclipse editor
	jdk 1.8
	junit-3.8.1.jar
	mavenized project




Assumptions:

1. Only .txt files are allowed
2.Data provided in file will be same as sample.txt
3.Order and time column will be always separated by space.
4.time provided in source file will be in epoch time
5.Human entered data (it can be anything) is used as it is.
6.In destination file Date will be displayed in below format
	Month dd,YYYY  hh:mm am/pm(Ex:March 07,2018 2.24 am)
7.Sorting: date & time  in ascending order and then alphabetically
8.Destination File : 
		a.if you provide .txt file then timestamp will be concatenated with given file name just to avoid data loss.
		b.if you provide just directory then new file will be created with name HumanReadableFile_<timestamp>
		c.Timestamp format will be MMMMddyyyyHHmmss(24 hour)
9.Test driven development is followed.




Test Results:
Test Case 1 : Happy Case
Source File: 1
Order		time
12at		1506176687
aizza		1477578287
51zza		1477491887
aaaa		1
abaizza		1477319087
bread		1477232687
abbread		1474640687
meatMeaT	1474295087
MeatMeaT	1474295087
VegVeg		1474295087
aaaaread	-436001729
aabaread	1477405487
aaaazead	1477405487


Destination File:1
Order Name			Date & Time of Order

aaaaread			March 8,1956 4:24,PM

aaaa			January 1,1970 12:00,AM

meatMeaT			September 19,2016 2:24,PM

MeatMeaT			September 19,2016 2:24,PM

VegVeg			September 19,2016 2:24,PM

abbread			September 23,2016 2:24,PM

bread			October 23,2016 2:24,PM

abaizza			October 24,2016 2:24,PM

aaaazead			October 25,2016 2:24,PM

aabaread			October 25,2016 2:24,PM

51zza			October 26,2016 2:24,PM

aizza			October 27,2016 2:24,PM

12at			September 23,2017 2:24,PM


Test Case 2 : Invalid data in time column

Source File: 2
Order		time
12at		1506176687
aizza		1477578287
51zza		1477491887
aaaa		ggfdgf
abaizza		1477319087
bread		1477232687
abbread		1474640687
meatMeaT	1474295087
MeatMeaT	1474295087
VegVeg		1474295087
aaaaread	-436001729
aabaread	1477405487
aaaazead	1477405487

Destination File:2
Order Name			Date & Time of Order

aaaaread			March 8,1956 4:24,PM

aaaa			Invalid Data

meatMeaT			September 19,2016 2:24,PM

MeatMeaT			September 19,2016 2:24,PM

VegVeg			September 19,2016 2:24,PM

abbread			September 23,2016 2:24,PM

bread			October 23,2016 2:24,PM

abaizza			October 24,2016 2:24,PM

aaaazead			October 25,2016 2:24,PM

aabaread			October 25,2016 2:24,PM

51zza			October 26,2016 2:24,PM

aizza			October 27,2016 2:24,PM

12at			September 23,2017 2:24,PM



Test Case 3: case sensitivity is not considered
Source File: 3
Order		time
12at		1506176687
aizza		1477578287
51zza		1477491887
aaaa		65356
abaizza		1477319087
bread		1477232687
abbread		1474640687
meatMeaT	1474295087
MeatMeaT	1474295087
VegVeg		1474295087
aaaaread	-436001729
aabaread	1477405487
aaaazead	1477405487
aaaareai	1520526271

Destination File:3
Order Name			Date & Time of Order

aaaaread			March 8,1956 4:24,PM

aaaa			January 1,1970 6:09,PM

meatMeaT			September 19,2016 2:24,PM

MeatMeaT			September 19,2016 2:24,PM

VegVeg			September 19,2016 2:24,PM

abbread			September 23,2016 2:24,PM

bread			October 23,2016 2:24,PM

abaizza			October 24,2016 2:24,PM

aaaazead			October 25,2016 2:24,PM

aabaread			October 25,2016 2:24,PM

51zza			October 26,2016 2:24,PM

aizza			October 27,2016 2:24,PM

12at			September 23,2017 2:24,PM

aaaareai			Future Order:March 8,2018 4:24,PM







