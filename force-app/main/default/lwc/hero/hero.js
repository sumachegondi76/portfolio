import { LightningElement } from 'lwc';
import Image_Url from '@salesforce/resourceUrl/portifolioImages';
export default class Hero extends LightningElement {
    heroImage = Image_Url + '/portfolio/hero1.jpg';
    connectedCallback() {
        console.log('Hero Image URL:', this.heroImage);
    }
}