public class TriggerAccountHandler {
    
    public static void updateRating(List<Account>accList)
    {
        for(Account acc : accList)
        {
            if(acc.industry != null && acc.industry =='Media')
            {
                acc.Rating ='Hot';
            }
        }
    }
  
    public static void updateAddress(list<Account> accList)
    {
        for(Account acc1 : accList)
        {
          //  if(acc1.copyBillingToShipping__c && acc1.BillingCountry != null && acc1.BillingState != null && acc1.BillingPostalCode != null)
            {
                acc1.ShippingCountry = acc1.BillingCountry;
                acc1.ShippingState = acc1.BillingState;
                acc1.ShippingPostalCode = acc1.BillingPostalCode;
                   
            }
        }
    }
    
    public static void createContact(List<Account>accList)
    {
       List<Contact> conList = new List<Contact>();
        
        for(Account acc : accList)
        {
            Contact con = new Contact();
                   con.FirstName = acc.Name;
                   con.LastName = acc.Name;
                   con.AccountId = acc.Id;
                   con.Phone = acc.Phone;
                   con.Email ='sadafnamdar@gmail.com';
                   conList.add(con);
        }
        if(!conList.isEmpty())
        {
            insert conList;
        }
    }
    public static void createRelatedOpportunity(List<Account> accList)
    {
        List<Opportunity> oppList = new List<Opportunity>();
        
           for(Account acc : accList)
           {
               Opportunity opp = new Opportunity();
                  
                      opp.Name = acc.Name;
                      opp.AccountId = acc.Id;
                      opp.StageName = 'Prospecting';
                      opp.CloseDate = system.today();
                      oppList.add(opp);
           }
        if(!oppList.isEmpty())
        {
            insert oppList;
        }
    }
        
  public static void createConOrOpp(List<Account> accList)
  {
      List<Contact> conList = new List<Contact>();
      List<Opportunity> oppList = new List<Opportunity>();
      
      for(Account acc : accList)
      {
          if(acc.contact__c)
          {
               Contact con = new Contact();
                 con.LastName = acc.Name;
                 con.AccountId = acc.Id;
                 conList.add(con);
          }
             
          if(acc.opportunity__c && acc.Active__c == 'yes')
          {
              Opportunity opp = new Opportunity();
                          opp.Name = acc.Name;
                          opp.StageName = 'Prospecting';
                          opp.AccountId = acc.Id;
                          oppList.add(opp);
          }
      }
      if(!oppList.isEmpty())
      {
          insert oppList;
      }
      if(!conList.isEmpty())
      {
          insert conList;
      }
  }
   public static void updateDescription(List<Account>accList, Map<Id,Account>oldMap)
   {
      for(Account acc : accList)
      {
          if(acc.phone!= oldMap.get(acc.Id).phone)
          {
              acc.Description = 'Phone is updated! OldValue : ' + oldMap.get(acc.Id).phone + '& NewValue : ' + acc.Phone;
          }
      }
   }
   public static void copyBillTOShip(List<Account>accList, Map<Id, Account> oldMap)
   {
       for(Account acc : accList)
       {
           if((oldMap==null && acc.copy__c)||(!oldMap.get(acc.Id).copy__c && acc.copy__c))
           {
               acc.ShippingCity = acc.BillingCity;
               acc.ShippingCountry = acc.BillingCountry;
               acc.ShippingCountryCode = acc.BillingCountryCode;
           }
       }
   }
    public static void updateRating(List<Account>accList, Map<Id, Account> oldMap)
    {
        for(Account acc: accList)
        {
            if((oldMap == null && acc.Industry == 'Media')||(acc.Industry == 'Media' && acc.Industry != oldMap.get(acc.id).Industry))
            {
                acc.Rating = 'Hot';
            }
        }
    }
    public static void updateRelatedContacts(List<Account> accList, Map<Id, Account> oldMap)
    {
        List<Contact> conList = new List<Contact>();
        Map<Id, Account> accToNewMap = new Map<Id, Account>();
            for(Account acc : accList)
            {
                if(oldMap != null && acc.Phone != null && acc.Phone != oldMap.get(acc.Id).phone)
                {
                    accToNewMap.put(acc.Id,acc);
                }
            }
        for(Contact con : [select id, HomePhone, AccountId from Contact where AccountId IN : accToNewMap.keySet()])
        {
            if(accToNewMap.containsKey(con.AccountId))
            {
                con.HomePhone = accToNewMap.get(con.AccountId).Phone;
                {
                    conList.add(con);
                }
            }
            
        }
        if(!conList.isEmpty())
        {
            update conList;
        }
    }
    public static void updateRelatedContactMail(List<Account> accList, Map<Id, Account> oldMap)
    {
        List<Contact> conList = new List<Contact>();
        Map<Id, Account> accToNewMap = new Map<Id, Account>();
        
        for(Account acc : accList)
        {
            if((oldMap != null && acc.BillingCountry != oldMap.get(acc.Id).BillingCountry ||
                                 acc.BillingCity != oldMap.get(acc.Id).BillingCity ||
                                 acc.BillingAddress != oldMap.get(acc.Id).BillingAddress ||
                                 acc.BillingCountryCode != oldMap.get(acc.Id).BillingCountryCode))
            {
                accToNewMap.put(acc.Id, acc);
            }
        }
        for(Contact con : [select id, AccountId from Contact where AccountId IN : accToNewMap.keySet()])
        {
            if(accToNewMap.containsKey(con.AccountId))
            {
               con.MailingState = accToNewMap.get(con.AccountId).BillingState;
               con.MailingCountry = accToNewMap.get(con.AccountId).BillingCountry;
               con.MailingCity = accToNewMap.get(con.AccountId).BillingCity;
               con.MailingCountryCode = accToNewMap.get(con.AccountId).BillingCountryCode;
                {
                    conList.add(con);
                }
            }
        }
        if(!conList.isEmpty())
        {
            update conList;
        }
    }
    public static void updateAddress1(List<Account>accList)
    {
        for(Account acc : accList)
        {
            if(acc.BillingAddress != null && acc.Matching_Billing_Address__c == true)
            {
                acc.ShippingPostalCode = acc.BillingPostalCode;
            }
        }
    }
    public static void updateStageName(List<Account>accList, Map<Id, Account> oldMap)
    {
        List<Opportunity> oppList = new List<Opportunity>();
        Set<id> idSets = new Set<id>();
        for(Account acc : accList)
        {
           if(acc.Active__c == 'No' && acc.Active__c != oldMap.get(acc.Id).Active__c)
           {
               idSets.add(acc.Id);
           }
        }
        for(Account acc1 : [select id, Active__c,(select id, StageName from opportunities)from Account where id IN : idSets])
        {
            if(acc1.opportunities != null)
            {
              for(Opportunity opp : acc1.opportunities)  
              {
                  if(opp.StageName != 'Closed Won' && opp.StageName != 'Closed Lost')
                  {
                      opp.StageName = 'ClosedLost';
                        oppList.add(opp);
                  }
              }
            }
        }
        if(oppList.isEmpty())
        {
            update oppList;
        }
    }
    public static void preventDeletion(List<Account> accList) {

    for (Account acc : accList) {
        if (acc.Active__c == 'Yes') {
            acc.addError(Label.Prevent_Account_Deletion);
        }
    }
}
public static void preventUpdate(List<Account> accList)
{
    for(Account acc : accList)
    {
        if(acc.CreatedDate < system.today()-6)
        {
            acc.addError('you cannot update account created 7days back');
        }
    }
}
    public static void checkProfileForDeletion(List<Account>accList)
    {
        Profile p = [select id from profile where Name = 'System Administrator'];
        
        for(Account acc : accList)
        {
            if(userInfo.getProfileId() != p.Id)
            {
                acc.addError('only system Administrator can delete Account');
            }
        }
    }
    public static void preventDeleteIfRelatedOpportunities(List<Account> accList)
    {
        set<Id> idSets = new set<Id>();
        
        for(Account acc : accList)
        {
            idSets.add(acc.id);
        }
        for(Account acc1 : [select id,(select id from opportunities)from Account where id IN : idSets])
        {
            if(acc1.opportunities.size()>0)
            {
                acc1.addError('you can not delete Account where related opportunities are available');
            }
        }
    }
    public static void preventDeleteIfRelatedCases(List<Account>accList)
    {
        set<Id>idSets = new set<Id>();
        
        for(Account acc : accList)
        {
            idSets.add(acc.Id);
        }
        for(Account acc1 : [select id,(select id from cases)from Account where id IN : idSets])
        {
            if(acc1.Cases.size()>0)
            {
                acc1.addError('you can not delete account where cases are related to account');
            }
        }
    }
    
    public static void preventAccount(List<Account> oldAccounts){
        Set<Id> accIds = new Set<Id>();
        
        for(Account account : oldAccounts){
            accIds.add(account.Id);
        }
        
        Map<Id, Integer> accOpposMap = new Map<Id, Integer>();
        
        for(AggregateResult ar : [SELECT AccountId accId, COUNT(Id) noOfOppos
                                  FROM Opportunity
                                  WHERE AccountId IN: accIds AND isClosed = False
                                  GROUP BY AccountId]){
            accOpposMap.put((Id) ar.get('accId'), (Integer) ar.get('noOfOppos'));
        }
        
        for(Account account : oldAccounts){
            if(accOpposMap.containsKey(account.Id)){
                account.AddError('You cannot delete this Account because it has related open opportunities');
            }
        }
                                                              
    }
      
}