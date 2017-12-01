trigger OpTrigger on Opportunity (before insert) {
    
    OpportunityHandler.handle(Trigger.new);
}