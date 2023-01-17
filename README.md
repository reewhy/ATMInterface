# ATMInterface

Simulation of an ATM in Java with Swing, MySQL, json and JavaMail (with Gmail SMTP).

Main page

![Main page](https://user-images.githubusercontent.com/78371731/213026980-e5ac8abb-4d63-4de6-8d44-3a689569fc6b.png)

Register page: input your datas and it will create an element in sql, automatically generate a tag, send a verification code to the email you entered and create the pin for your account.
![image](https://user-images.githubusercontent.com/78371731/213027060-127973ab-b348-4b45-a295-f74e13721c60.png)

![image](https://user-images.githubusercontent.com/78371731/213027489-c5ff7e7e-15f9-4808-9b35-134a7c96e4a4.png)

Email sent to verify the account

![image](https://user-images.githubusercontent.com/78371731/213028937-72a9a841-58cb-4305-b99b-4b641e12c040.png)

Login page: input name and pin to login, click on "I forgot my pin..." to reset it.

![image](https://user-images.githubusercontent.com/78371731/213029034-4f8b31fb-c06f-46c2-8628-f30313e3b315.png)

Password reset: input datas and the software will automatically search for the account's email

![image](https://user-images.githubusercontent.com/78371731/213029175-d0c70c4d-e7fe-4fbe-8e7f-37933d271912.png)

It will send an email and wait for the confirm code

![image](https://user-images.githubusercontent.com/78371731/213029273-70454dd7-984c-4f21-a187-aaccfc0bcf90.png)

It will ask you for the new pin

![image](https://user-images.githubusercontent.com/78371731/213029336-97b74f04-8973-4532-a074-0d90a7c1539a.png)

On first login it will ask you to insert the verification code sent to your email, after inserting the code you wont need to insert it again

![image](https://user-images.githubusercontent.com/78371731/213029440-cd44b5f9-ecac-4413-bb01-343d0930e149.png)

## User page

In user page you will be able to do different actions

![image](https://user-images.githubusercontent.com/78371731/213029596-89beafbd-fbc3-492e-92c1-ebd800d09d7e.png)

##### Withdraw

It will ask  you the amount to withdraw

![image](https://user-images.githubusercontent.com/78371731/213029717-1190832b-5329-42f9-afe5-88fb3ef04686.png)

and then it will ask you to confirm your pin

![image](https://user-images.githubusercontent.com/78371731/213029787-bbecc371-b3f7-4266-a45d-265474882ded.png)

##### Deposit

It will ask you the amount to deposit, then you'll need to confirm your pin

![image](https://user-images.githubusercontent.com/78371731/213029859-ec3f94eb-cd9f-4ff5-bab4-0ea9811cfd27.png)

##### Send

It will ask you the amount to send

![image](https://user-images.githubusercontent.com/78371731/213029955-37290806-6f6c-4118-8961-a9badd74b8cf.png)

then he will ask you to confirm your PIN

![image](https://user-images.githubusercontent.com/78371731/213030026-cfa0d861-8ef8-4613-88bb-7cfc0bb398cf.png)

lastly it will ask te receiver's ID/tag

![image](https://user-images.githubusercontent.com/78371731/213030080-2f56450b-c6e6-4f5b-93ac-0b854aa3d7a9.png)

##### View

It will show your balance

![image](https://user-images.githubusercontent.com/78371731/213030181-b175632a-3f54-49cb-9167-5c767026e3c8.png)

##### Settings

You can change your profile email, phone and pin. After using this function you'll need to re-verify your account

![image](https://user-images.githubusercontent.com/78371731/213030355-4f2105ef-12d4-41cf-a5e7-094011f57303.png)

##### Logout

It will log you out of the account

## Admin page

If you login with an admin account you'll find a new button

![image](https://user-images.githubusercontent.com/78371731/213030545-5769c957-bcbb-4177-adce-12d844eb858b.png)

If you go in the admin tab, you'll find new options

![image](https://user-images.githubusercontent.com/78371731/213030618-f9c19dcb-91ef-4da3-a9d8-1c0ae0e0150a.png)

##### Show

It will show all the accounts and theirs infos in a table

![image](https://user-images.githubusercontent.com/78371731/213030864-44bdf3c8-cfab-4711-a876-b4e5fea1f62a.png)

##### Create

It will let you create a new account and also decide if it is going to be an admin account

![image](https://user-images.githubusercontent.com/78371731/213031410-7f6d1787-0b94-4159-a1cb-59bd1e25059a.png)

##### Delete

It will let you delete an account

![image](https://user-images.githubusercontent.com/78371731/213031476-7c4f163c-fbdd-4815-9826-503beac6989a.png)

##### Change

It will ask the account's tag and then it will let you change the account's infos

![image](https://user-images.githubusercontent.com/78371731/213031630-448fda22-31f4-4e46-a78d-e73009ec8210.png)

##### Reset

It will reset the entire database, except the logged account

##### Settings

You'll be able to change the SQL database settings

![image](https://user-images.githubusercontent.com/78371731/213032049-52e9f0f8-5729-44a5-b382-2ec8cf3c4d8c.png)


