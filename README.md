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

### [BONUS] Digital Wireframes & Mockups
#### Wireframe Link: https://www.figma.com/file/KJusfudVAfpp5y9wHIzaW7/Protoype?node-id=0%3A1
<img src='https://i.ibb.co/brLhN5g/Screen-Shot-2021-04-02-at-7-34-33-PM.png' width=500>

### [BONUS] Interactive Prototype
<img src='https://github.com/LCIS-codepath/Appointsy/blob/main/appointsy_prototype.gif' title='Video Walkthrough' width='250' alt='Video Walkthrough' />


## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
