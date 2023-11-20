from instabot import Bot
from PIL import Image
import requests
from io import BytesIO

bot = Bot()
def accountLogin(userName,passWOrd):
 try:
  bot.login(username=userName, password=passWOrd)
  return "Login successfully."
 except Exception as e:
  return e

######  upload a picture #######
def post_photo(imageURL,postCaption):
 response = requests.get(imageURL)
 img = Image.open(BytesIO(response.content))
 bot.upload_photo(img, caption=postCaption)

######  follow someone #######
def followUser(username):
 bot.follow(username)

######  send a message #######
def send_msg(message):
 bot.send_message(message, ['user1','user2'])

######  get follower info #######
def get_user_followersList(username):
 my_followers = bot.get_user_followers(username)
 for follower in my_followers:
    print(follower)

def unfollow_all():
 bot.unfollow_everyone()