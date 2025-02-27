import { LightningElement } from 'lwc';
import IMAGE_URL from '@salesforce/resourceUrl/portifolioImages';
export default class CertificationSection extends LightningElement {
    certificationData=[
        {
            id:1,
            name:"Platform developer 1",
            date:"1/08/2023",
            certid:"2857438",
            image:IMAGE_URL+'/portfolio/pd1.png'
        },
        {
            id:1,
            name:"Salesforce Associate",
            date:"5/14/2023",
            certid:"3397748",
            image:IMAGE_URL+'/portfolio/associate.png'
        },
        {
            id:1,
            name:"AI Associate",
            date:"10/17/2024",
            certid:"5066955",
            image:IMAGE_URL+'/portfolio/AiAssociate.png'
        },
        {
            id:1,
            name:"AI Specialist",
            date:"10/10/2023",
            certid:"5002839",
            image:IMAGE_URL+'/portfolio/Aispecialist.png'
        },
    ]
}