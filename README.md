# Appointsy

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

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

### User
* Login to view nearby businesses
* Filter nearby businesses by location/favorites
* Register through sign up 
* Favorited/liked businesses
* Notifications and Reminders of Appointment
* Setup profile

#### Business
* Sign up or log in
* Create business profile
* Set availability hours
* View all upcoming appointments

#### Overall
* Infinite scrolling
* Floating Action Button to compose an appointment (user/business)
* Swipe to refresh
* Modal overlays (fragments)
* Bottom Navigation Bar
* Data binding
* Google API Maps
* Google API Calendar
* Phone number confirmation

**Optional Nice-to-have Stories**

* Maps Archetype Screen
   * Show the nearby businesses in a map screen
* Login with Google
   * Login with Google™ functionality
* Offline persistence
* MVVM
* GitHub Actions setup
    * CI - build
    * CD - deploy


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
<img src='https://i.ibb.co/brLhN5g/Screen-Shot-2021-04-02-at-7-34-33-PM.png' width=500>

### [BONUS] Interactive Prototype
<img src='https://github.com/LCIS-codepath/Appointsy/blob/main/appointsy_prototype.gif' title='Video Walkthrough' width='250' alt='Video Walkthrough' />


## Schema 
## Models 

#### Login

| Property    | Type        |Description |
| ----------- | ----------- |----------- |
| ObjectID    | Integer     | Unique id for user          |
| Email       | String      | Unique user email         |
| Password    | Char        | User password           |
| PhoneNumber | Integer     |  Optional/Required  |

#### User Profile

| Property    | Type        |Description |
| ----------- | ----------- |----------- |
| ObjectID    | Title       |Unique profile ID            |
| Name        | String      |User's full name            |
| Email       | String      |User's unique email            |
| Password    | String      |User's password            |
| Image       | File        | Optional profile image |
| Bio         | String      | Optional bio for user           |
|Notifiction Freq.|   Int   | Number of times the user will be notified of their appointments           |

#### Business Profile 

| Property    | Type        |Description |
| ----------- | ----------- |----------- |
| ObjectID    | Integer     |Unique object ID            |
| Business name | String    |Business's name            |
| Owner name  | String      |Owner's name |
| Email       | String      |Unique email            |
| Password    | String      |Business' password            |
| Image       | File        | Business image |
| Bio         | String      |Optional bio           |
| Location    |   Int       |Business location           |
| Hours       |   Int       | Business hours of service |
| Service/Avg Price|   Int  |  Average price of service       |
| Business Type|   String   |  Type of bussiness   |

#### Settings

| Property      | Type    | Description    |
| ------------- | ------- |:-------------- |
| Dark Theme    | Boolean |  Set light or dark theme of the app              |
| Notifications | Boolean |  If the user wants notifications             |
| Credits       | String  |  Developers who worked on app   |
| App Version   | String  | What revision the  app is on               |
| Release Notes | String  |  Release notes of the app    |
| License/Legal | File    |  License about the app              |

#### Appointments
| Property    | Type        |Description |
| ----------- | ----------- |----------- |
| ObjectID    | Integer     | Unique ID        |
| Name        | String      | Name of the appointee           |
| Time        | String      | Time of the appoinment          |
| Details     | String      | Information about the appoinment           |
| Status      | Boolean     | Indicate if appoinment is canceled or confirmed           |
| Image       | File        | Business image 
| isReschedule| Boolean     | User can reschedule only 1 time           |

### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
