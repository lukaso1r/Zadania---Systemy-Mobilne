-(IBAction)enter {
    NSString *yourName = self.inputField.text;
    NSString *myName = @"YourName";
    NSString *message;

    if ([yourName length] == 0) {
        yourName = @"World";
    }

    if ([yourName isEqualToString:myName]) {
        message = [NSString stringWithFormat:@"Hello %@! We have the same name :)", yourName];
    }else {
        message = [NSString stringWithFormat:@"Hello %@!", yourName];
    }

    self.messageLabel.text = message;
}

-(void) addItemViewController:(SecondViewController *)controller didFinishEnteringItem:(NSString *)item {
    NSlog(@"This was returned from SecondViewController %@", item);
    self.surnameTextField.text = item;
}