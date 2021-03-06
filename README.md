# GhostCat
Repository for the CS 452 group working on the Ghost Cat project


# Project description
## Project background and overview
Magpie Labs has spun out a service called Ghost Cat Analytics (ghostcat.io), to analyze
the content of remote camera images used to track wildlife in large-scale studies. The
service outputs predictions of what species and how many of each are in images.
Previous clients have mostly just required large .csv summaries of the contents of their
images, but future clients will want a web interface.

## What do you anticipate the team will build?
  The goal of the project will be to build a web application that will enable wildlife
researchers to store their data. The app could do things such as:
  Provide a GUI for uploading new data into a database
  Provide a GUI for fetching images and filtering them by date ranges and species, as defined in the image metadata and ML model outputs.
 
## Architectural, technology and integration considerations
  Currently, a lot of data is stored on Amazon s3 buckets, but we intend our project to store the data using AWS databases. For the NoSQL database, this means we anticipate using DynamoDB.

## Legal Considerations
  We have been given permission to use data from Ghost Cat Analytics by David Healy (an employee at Ghost Cat). This information is also not super confidential, because it consists mostly of images of wildlife, so we do not anticipate running into too many issues regarding data privacy.

## Business Considerations
  While this may seem like a niche project, the business value of this product is quite large.  Our team member at Ghost Cat has many users that are anxiously awaiting this software integration to allow for easier access to their photos for simplified population tracking. 
