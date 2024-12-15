import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FooterComponent } from './footer.component';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';

describe('FooterComponent', () => {
  let component: FooterComponent;
  let fixture: ComponentFixture<FooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule,FooterComponent], // Import RouterTestingModule to support RouterLink
    }).compileComponents();

    fixture = TestBed.createComponent(FooterComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should display the correct current year', () => {
    // Arrange
    const currentYear = new Date().getFullYear();

    // Act
    fixture.detectChanges(); // Trigger change detection
    const yearElement = fixture.debugElement.query(By.css('.text-center')).nativeElement;

    // Assert
    expect(yearElement.textContent).toContain(`© ${currentYear}`);
  });

  it('should display the brand name "SunTravels"', () => {
    // Act
    fixture.detectChanges();
    const brandElement = fixture.debugElement.query(By.css('h3')).nativeElement;

    // Assert
    expect(brandElement.textContent).toBe('SunTravels');
  });

  it('should render quick links with correct router links', () => {
    // Act
    fixture.detectChanges();
    const links = fixture.debugElement.queryAll(By.css('.quick-links a'));

    // Assert
    const routerLinks = links.map((link) => link.attributes['ng-reflect-router-link']);
    expect(routerLinks).toEqual(['', 'search', 'hotel']);
  });

  it('should display contact details correctly', () => {
    // Act
    fixture.detectChanges();
    const emailElement = fixture.debugElement.query(By.css('a[href^="mailto"]')).nativeElement;
    const phoneElement = fixture.debugElement.query(By.css('a[href^="tel"]')).nativeElement;

    // Assert
    expect(emailElement.textContent).toBe('support suntravels.com');
    expect(phoneElement.textContent).toBe('+1 234 567 890');
  });

  it('should apply hover styles for links (UI test)', () => {
    // This test would typically be done visually or using a CSS-in-JS approach.
    // Here, we check the presence of hover styles in the class list.
    const linkElement = fixture.debugElement.query(By.css('a')).nativeElement;

    expect(linkElement.classList).toContain('hover:text-gray-200');
  });
});
