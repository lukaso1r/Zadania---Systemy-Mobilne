@property (nonatomic, weak) IBOutlet UILabel *messageLabel;
@property (nonatomic, weak) IBOutlet UITextField *inputField;

#import "SecondViewController.h"

@interface ViewwController : UIViewController <SecondViewControllerDelegate>

-(IBAction)enter 

-(void) prepareForSegue:(UIStoryboard *)segue sender:(id)sender{
    if([segue.identifier isEqualToString:@"sendSurnameSegue*]){
        SecondViewController *controller = (SecondViewController *) segue.destinationViewController;
        controller.surname = self.surnameTextField.text;
    }"])

    controller.delegate = self;
}
