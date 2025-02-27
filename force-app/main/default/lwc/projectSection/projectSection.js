import { LightningElement } from 'lwc';
import IMAGE_URL from '@salesforce/resourceUrl/portifolioImages';

export default class ProjectSection extends LightningElement {
    projectImage = IMAGE_URL+'/portfolio/project3.jpg';
    projectData=[
        {
            id:1,
            name:'Online Assessment',
            description:'Online Assessment is used to conduct online exams for candidates based on their domain and years of experience. All the information about the candidate is stored in the candidate result data table.',
            technology:'Salesforce, LWC, Apex, Javascript, Experience Cloud'
        },
        {
            id:2,
            name:'Confernce Room Booking System',
            description:'The conference room booking system in Salesforce is designed to provide a user-friendly experience, allowing users to easily search for available rooms based on date, floor, and time slot. It features real-time availability indicators with color codes, where green represents available rooms and red indicates booked ones.',
            technology:'Salesforce, LWC, Apex, Javascript, Experience Cloud',
            website:'https://roothootcom-dev-ed.my.site.com/ConferenceRoom/'
           
        }
    ]
}