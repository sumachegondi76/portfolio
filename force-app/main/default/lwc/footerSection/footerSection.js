import { LightningElement } from 'lwc';
import Image_url from '@salesforce/resourceUrl/portifolioImages';
export default class FooterSection extends LightningElement {
    linkedinLogo = Image_url+'/portfolio/linkedin.png';
    trailheadLogo = Image_url+'/portfolio/trailhead.png';
    sfLogo = Image_url+'/portfolio/salesforce.jpg';
}