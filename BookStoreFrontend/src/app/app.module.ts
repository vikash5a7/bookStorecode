import { WishComponent } from './Component/wish/wish.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToolbarComponent } from './Component/toolbar/toolbar.component';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatSidenavModule} from '@angular/material/sidenav';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatPaginatorModule} from '@angular/material/paginator';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { DisplaybookComponent } from './Component/displaybook/displaybook.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSnackBarModule } from '@angular/material/snack-bar';
import {MatBadgeModule} from '@angular/material/badge';
import {MatSelectModule} from '@angular/material/select';
import { BooksearchpipePipe } from './Pipe/booksearchpipe.pipe';
import { CartComponent } from './Component/cart/cart.component';
import {MatStepperModule} from '@angular/material/stepper';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatRadioModule} from '@angular/material/radio';
import { OrdergreetingComponent } from './Component/ordergreeting/ordergreeting.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { PagenotfoundComponent } from './component/pagenotfound/pagenotfound.component';
import { ForgetPasswordComponent } from './Component/auth/forget-password/forget-password.component';
import { ResetPasswordComponent } from './Component/auth/reset-password/reset-password.component';
import { AdminComponent } from './Component/admin/admin/admin.component';
import { SpineerComponent } from './Component/spineer/spineer.component';
import { LoginComponentComponent } from './Component/auth/login-component/login-component.component';
import { RegistrationComponent } from './Component/auth/registration/registration.component';
import { SellerComponent } from './Component/seller/seller/seller.component';
import { FooterComponent } from './Component/footer/footer.component';
import { DashboardComponent } from './Component/dashboard/dashboard.component';
import { SidenavbarComponent } from './Component/sidenavbar/sidenavbar.component';
import { AddbookComponent } from './Component/addbook/addbook.component';
import { MatDialogModule } from '@angular/material/dialog';
import { UpdateBookComponent } from './Component/update-book/update-book.component';
import { GiverateComponent } from './Component/giverate/giverate.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { AdminunverifiedbooksComponent } from './Component/adminunverifiedbooks/adminunverifiedbooks.component';
import { RatereviewComponent } from './Component/ratereview/ratereview.component';
import { RatedbooksComponent } from './Component/ratedbooks/ratedbooks.component';
import { BookreviewsComponent } from './Component/bookreviews/bookreviews.component';
import { OrderstatusComponent } from './Component/orderstatus/orderstatus.component';
import { UploadBookImageComponent } from './Component/addbook/upload-book-image/upload-book-image.component';



@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    DisplaybookComponent,
    BooksearchpipePipe,
    CartComponent,
    OrdergreetingComponent,
    PagenotfoundComponent,
    ForgetPasswordComponent,
    ResetPasswordComponent,
    AdminComponent,
    SpineerComponent,
    LoginComponentComponent,
    RegistrationComponent,
    SellerComponent,
    FooterComponent,
    DashboardComponent,
    SidenavbarComponent,
    GiverateComponent,
    AdminunverifiedbooksComponent,
    RatereviewComponent,
    AddbookComponent,
    UpdateBookComponent,
    UploadBookImageComponent,
    BookreviewsComponent,
    OrderstatusComponent,
    RatedbooksComponent,
    WishComponent

  ],
  imports: [
    MatBadgeModule,
    BrowserModule,
    MatToolbarModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatPaginatorModule,
    FormsModule,
    MatCardModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatStepperModule,
    MatCheckboxModule,
    MatRadioModule,
    MatIconModule,
    FlexLayoutModule,
    MatMenuModule,
    MatButtonModule,
    MatSidenavModule,
    MatSelectModule,
    MatTooltipModule,
    MatDialogModule,
 ],
  providers: [HttpClient],
  bootstrap: [AppComponent],

})
export class AppModule { }
