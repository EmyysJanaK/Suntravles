import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import {provideRouter} from '@angular/router';
import {importProvidersFrom} from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {routes} from './app/app.routes';
bootstrapApplication(AppComponent, {providers: [provideRouter(routes), importProvidersFrom(HttpClientModule)]}).catch((err) => console.error(err));
