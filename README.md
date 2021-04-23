# Appointsy

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)
3. [App Updates](#App-Updates)

## Overview
### Description
An application for businesses that allows clients to schedule appointments through their availability.

### App Evaluation
- **Category:** Business/Social Network.
- **Mobile:** Primarily developed for Android mobile applications but may extend the idea to the web and iOS.
- **Story:** Provides the user with nearby local businesses depending on the specified filters.
- **Market:** Small businesses and their clients.
- **Habit:** The app will be used as often as the user needs to schedule appointments for their daily lists and neccessities. 
- **Scope:** Business would primarily post their availabilty on the app, which will allow users to specify their appointment. That will provide a bussiness with a confirmation of the appointment. Provide both the bussiness and individuals with a reminder. 

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**



#### User
* [x]  User can login & register
* [ ]  User can favorite Businesses
* [ ] Login to view nearby businesses
* [ ] Filter nearby businesses by location/favorites
* [x] Register through sign up 
* [ ] Favorited/liked businesses
* [ ] Notifications and Reminders of Appointment
* [ ] Setup profile
* [ ]  Google Maps API to show distance between User and Business

#### Business
* [ ] Sign up or log in
* [ ] Create business profile
* [ ] Set availability hours
* [ ] View all upcoming appointments

#### General
* [x] UI Setup
* [ ] Custom App Icon
* [ ] Infinite scrolling
* [ ] Floating Action Button to compose an appointment (user/business)
* [ ] Swipe to refresh
* [ ] Modal overlays (fragments)
* [ ] Bottom Navigation Bar
* [ ] Data binding MVVM
* [ ] Google API Maps
* [ ] Google API Calendar
* [ ]   Back4App connect
* [ ] Connect Google Calendar API to allow users to add their appointment

**Optional: Nice-to-have Stories**
* [ ]  Data Binding
* [ ]  Stride API Payment system for SaaS
* [ ]  Table Orders
* [ ]  Splash Screen
* [ ]  Business Analytics
* [ ]  Google Login API
* [ ]  Offline persistence (to view nearby places)
* [ ]  Login with Google™ functionality
* [ ]  Offline persistence
* [ ]  MVVM
* [ ]  Phone number confirmation
* [ ]  GitHub Actions setup
    * * [ ]  CI - build
    * * [ ] CD - deploy


### 2. Screen Archetypes

* Login
* Register - Setup business or user credentials
    * Phone number confirmation
* Home
    * Shows list of scheduled appointments
* Stream
   * Show list of local businesses 
   * User can filter by distance, favorited, and type of business
* Detail (Profile view)
   * Provide a detailed information from the profile
* Creation (Appointment)
   * View availability, create, reschedule, cancel appointments
   * Manange appointment(s) section
* Profile
   * Provide email, password, phone number
   * Setup photo, username, bio
   * Business: name, appointment availability, phone number, location, hours of operation
   * User: name, secheduled appointments (if it's with your business)
* Settings
   * Change theme light/dark mode
   * About
   * Logout

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Stream (Businesses)
* Home (Manage appoinments)
* Creation
* Profile
* Settings

**Flow Navigation** (Screen to Screen)

* Home
   * View created appointments
* Creation
   * Manage appointments
* Signup -> Profile setup
* Login -> Stream screen
* Stream -> detail view
* Stream -> compose
* Profile
   * Modify user attributes
* Settings
    * Show app settings

## Wireframes
#### Wireframe Link: https://i.ibb.co/SRBY2FF/wireframes.jpg
* Recommend viewing the image in a different tab and zooming in </br>
 to different parts of the image for a better understanding.
<img src='https://i.ibb.co/SRBY2FF/wireframes.jpg' width=500>


### [BONUS] Digital Wireframes & Mockups
#### Digital Wireframe Link: https://www.figma.com/file/KJusfudVAfpp5y9wHIzaW7/Protoype?node-id=0%3A1
![Design](https://user-images.githubusercontent.com/25497457/113488995-5c44da00-9476-11eb-8815-fc6adb12d295.png)

### [BONUS] Interactive Prototype
<img src='https://github.com/LCIS-codepath/Appointsy/blob/main/appointsy_prototype.gif' title='Video Walkthrough' width='250' alt='Video Walkthrough' />

## Schema 1.2
![Schema](https://user-images.githubusercontent.com/25497457/115927435-4903a980-a439-11eb-8412-e098d350194d.png)

## Models

#### User Profile

| Property    | Type        |Description |
| ----------- | ----------- |----------- |
| ObjectID    | Title       |Unique profile ID            |
| Name        | String      |User's full name            |
| Email       | String      |User's unique email            |
| Password    | String      |User's password            |
| Image       | File        | Optional profile image |
| Phone Number | Integer     |  Optional/Required  |
| Bio         | String      | Optional bio for user           |

#### Business Profile 

| Property    | Type        |Description |
| ----------- | ----------- |----------- |
| ObjectID    | Integer     |Unique object ID            |
| Business name | String    |Business's name            |
| Owner name  | String      |Owner's name |
| Email       | String      |Unique email            |
| Password    | String      |Business' password            |
| Image       | File        | Business image |
| Phone Number | Integer     |  Optional/Required  |
| Bio         | String      |Optional bio           |
| Location    |   Int       |Business location           |
| Hours       |   Int       | Business hours of service |
| Service/Avg Price|   Int  |  Average price of service       |
| Business Type|   String   |  Type of bussiness   |

#### Appointments

| Property    | Type        |Description |
| ----------- | ----------- |----------- |
| ObjectID    | Integer     | Unique ID        |
| Business Name| String     | Name of Business        |
| Name        | String      | Name of the appointee           |
| Time        | String      | Time of the appoinment          |
| Details     | String      | Information about the appoinment           |
| Status      | Boolean     | Indicate if appoinment is canceled or confirmed           |
| Image       | File        | Business image 
| isReschedule| Boolean     | User can reschedule only 1 time           |


### Networking

* Login
   * Google Login
        * (READ/GET) google access token
* Register - Setup business or user credentials
    * (CREATE/POST) Create new user profile for client/business
* Stream
    * (READ/GET) Appointment details
* Home 
    * Business
        * (READ/GET) get the list of events from user calendar
        * (UPDATE/PUT) Favorite
    * Client
        * (READ/GET) Get appointments from calendar
        * (Read/GET) Get distance of business in relation to users current location 
* Detail
    * Business desc.
        * (READ/GET) Get Business details
        * (Create/POST) Create new Google Maps intent for driving directions
        * (READ/GET) Get appointments from calendar
    * Appointment
        * (DELETE) Cancel appointment
        * (UPDATE/PUT) Reschedule appointment
        * (POST/CREATE) Intent for Maps directions
* Creation (Appointment)
    * (READ/GET) Get business details
    * (CREATE/POST) Add event to calendar
* Appointment Overlay
    * (Create/POST) Create a new appointments
    * (READ/GET) Get events from calendar
    * (READ/GET) Get business details
* Profile
    * (GET/CREATE) Create a new user Profile
* Settings
    * (POST/GET) User preference for dark mode
    * (POST/GET) Logout

--- Basic snippets for each Parse network request
```
public void createObject() {
  ParseObject entity = new ParseObject("Appointment");

  entity.put("BusinessName", "A string");
  entity.put("Time", "2323" );
  entity.put("Status", "Confirmed" );
  entity.put("Details", "Business 123" );
  entity.put("isReschedule", "false" );

  entity.put("user", ParseUser.getCurrentUser());

  // Saves the new object.
  // Notice that the SaveCallback is totally optional!
  entity.saveInBackground(e -> {
    if (e==null){
      //Save was done
    }else{
      //Something went wrong
      Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
  });
}
```
— End points & APIs
#### Google Maps API

- Base URL - http://maps.googleapis.com/maps/api
- Documentation - https://developers.google.com/maps/documentation/distance-matrix/overview#json

| HTTP VERB   | Endpoint                     |Description                        |
| ----------- | -----------------------------|-----------------------------------|
| GET         | distancematrix/rows/distance |gets distance between two locations|

#### Google Calendar API

- Base URL - https://www.googleapis.com/calendar/v3
- Documentation - https://developers.google.com/calendar/v3/reference


|HTTP VERB    |Endpoint                                      |Description              |
|-------------|----------------------------------------------|-------------------------|
|GET          |/calendars/calendarId/events/eventId          |Returns an event.        |


#### Stripe API

- Base URL - https://api.stripe.com
- Documentation - https://stripe.com/docs/development/quickstart

| HTTP VERB    | Endpoint        |Description |
| ----------- | ----------- |----------- |
| POST    | /create-payment-intent | Payment lifecycle |

#### Google Pay
- Documentation: https://developers.google.com/pay/api/android/overview
- Setup: https://developers.google.com/android/guides/setup

#### Google Login
- documentation: https://developers.google.com/identity/sign-in/android/start
- Reference: https://developers.google.com/android/reference/com/google/android/gms/auth/api/signin/GoogleSignInApi

## Open-Source Libraries & Resources
- iconmonstr


## App Updates
#### Current App Gif need to merge features
<img src='https://github.com/LCIS-codepath/Appointsy/blob/HomeStream/appointsy_gif1.gif' title='Video Walkthrough' 
width='250' alt='Video Walkthrough' style="margin-right: 100px;" />  
<img src='https://github.com/LCIS-codepath/Appointsy/blob/HomeStream/appointsygif2.gif' title='Video Walkthrough' width='250' alt='Video Walkthrough' />

#### Need to read data from back 4 app to display it's content
<img src='https://github.com/LCIS-codepath/Appointsy/blob/HomeStream/Appointment.png' title='Video Walkthrough' width='250' alt='Video Walkthrough' /> <img src='https://github.com/LCIS-codepath/Appointsy/blob/HomeStream/Business.png' title='Video Walkthrough' width='250' alt='Video Walkthrough' />






