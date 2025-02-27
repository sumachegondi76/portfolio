import { LightningElement } from 'lwc';
import Image_Url from '@salesforce/resourceUrl/portifolioImages';
import {NavigationMixin} from 'lightning/navigation';

export default class QuickLinkss extends NavigationMixin(LightningElement) {
    data=[
        {
            id:1,
            image:Image_Url+'/portfolio/project2.jpg',
            text:'Projects',
        },
        {
            id:2,
            image:Image_Url+'/portfolio/hero2.png',
            text:'Skills',
        },
        {
            id:3,
            image:Image_Url+'/portfolio/certification.jpg',
            text:'Certifications',
        }
    ];

    handleClick(event){
    let selectedCard = event.currentTarget.dataset.id;
    console.log('selectedCard:'+JSON.stringify(selectedCard));
       if(selectedCard == 1){
this.navigateToPages('project__c');
       }
       else if(selectedCard == 2){
this.navigateToPages('skills__c');
}
else{
    this.navigateToPages('certification__c');
}
    }
    
navigateToPages(pageApiName){
console.log('pageApiName in navigation function:'+JSON.stringify(pageApiName))

this[NavigationMixin.Navigate]({
    type: 'comm__namedPage',
    attributes:{
        name:pageApiName
    }
    })
}
}