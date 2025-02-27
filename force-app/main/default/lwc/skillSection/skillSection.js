import { LightningElement,api } from 'lwc';
import IMAGE_URL from '@salesforce/resourceUrl/portifolioImages';
export default class SkillSection extends LightningElement {
    @api skillName1 = "LWC"
    @api skillBarValue1 = "70";
    @api skillName2 = "Apex"
    @api skillBarValue2 = "80";
    @api skillName3 = "Rest Api Integration"
    @api skillBarValue3 = "60";
    @api skillName4 = "Flows"
    @api skillBarValue4 = "60";
    @api skillImage = IMAGE_URL+'/portfolio/skill.jpg';
}