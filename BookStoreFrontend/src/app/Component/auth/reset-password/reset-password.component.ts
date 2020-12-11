import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { UserService } from 'src/app/Service/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {
  public error = null;
  public isLoading = false;
  token: string;
  public form = {
    email: null,
    newPassword: null,
    confirmPassword: null,
  };
  constructor(private user: UserService,
              private route: Router,
              private matSnakeBar: MatSnackBar,
              private activatedRoute: ActivatedRoute
) {
}
ngOnInit() {
  this.activatedRoute.paramMap.subscribe((parameters: ParamMap) => {
  this.token = parameters.get('token');
  console.log(this.token);
  });
  }
handleError(error) {
  this.isLoading = false;
  this.error = error.error.message;
  console.log(error);
  this.matSnakeBar.open(this.error, 'ok', {
  duration: 5000
});
}
onSubmit() {
  this.isLoading = true;
  this.user.updatePassword(this.form, this.token).subscribe(
  data => this.handleResponse(data),
  error => this.handleError(error)
);
}
handleResponse(data) {
  this.isLoading = false;
  this.matSnakeBar.open('Sucessfull Update Password ', 'ok', {
  duration: 5000
  });
  this.route.navigateByUrl('\login');
}

}
