import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/Service/user.service";
import { Router } from "@angular/router";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: "app-forget-password",
  templateUrl: "./forget-password.component.html",
  styleUrls: ["./forget-password.component.scss"],
})
export class ForgetPasswordComponent implements OnInit {
  public isLoading = false;
  public error = null;
  message = null;
  public form = {
    email: null,
  };

  constructor(
    private user: UserService,
    private route: Router,
    private matSnackBar: MatSnackBar
  ) {}

  ngOnInit() {}
  onSubmit() {
    this.isLoading = true;
    this.user.forgetPassword(this.form).subscribe(
      (data) => this.handleResponse(data),
      (error) => this.handleError(error)
    );
  }
  handleError(error: { error: any }) {
    this.isLoading = false;
    this.error = error.error.message;
    this.matSnackBar.open(this.error, "ok", {
      duration: 5000,
    });
  }
  handleResponse(data) {
    this.isLoading = false;
    this.matSnackBar.open("Check Your Email Please ", "ok", {
      duration: 5000,
    });
    this.route.navigateByUrl("login");
  }
}
