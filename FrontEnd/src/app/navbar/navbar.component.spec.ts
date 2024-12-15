import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NavbarComponent } from './navbar.component';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule,NavbarComponent], // To handle RouterLink
    }).compileComponents();

    fixture = TestBed.createComponent(NavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should display the title "SunTravels"', () => {
    const titleElement = fixture.debugElement.query(By.css('.text-2xl')).nativeElement;
    expect(titleElement.textContent).toBe('SunTravels');
  });

  it('should render desktop navigation links with correct router links', () => {
    const desktopLinks = fixture.debugElement.queryAll(By.css('.md\\:flex a'));
    const routerLinks = desktopLinks.map((link) => link.attributes['ng-reflect-router-link']);
    expect(routerLinks).toEqual(['', 'search', 'hotel']);
  });

  it('should render mobile navigation links when the menu is toggled', () => {
    // Initially, the mobile menu should be closed
    expect(component.isMobileMenuOpen).toBeFalse();
    let mobileMenu = fixture.debugElement.query(By.css('.md\\:hidden.bg-blue-500'));
    expect(mobileMenu).toBeNull();

    // Simulate clicking the mobile menu button
    const toggleButton = fixture.debugElement.query(By.css('button')).nativeElement;
    toggleButton.click();
    fixture.detectChanges();

    // The mobile menu should now be open
    expect(component.isMobileMenuOpen).toBeTrue();
    mobileMenu = fixture.debugElement.query(By.css('.md\\:hidden.bg-blue-500'));
    expect(mobileMenu).not.toBeNull();

    // Check router links in the mobile menu
    const mobileLinks = mobileMenu.queryAll(By.css('a'));
    const routerLinks = mobileLinks.map((link) => link.attributes['ng-reflect-router-link']);
    expect(routerLinks).toEqual(['', 'search', 'hotel']);
  });

  it('should toggle mobile menu visibility on button click', () => {
    const toggleButton = fixture.debugElement.query(By.css('button')).nativeElement;

    // Initially, the mobile menu should be closed
    expect(component.isMobileMenuOpen).toBeFalse();

    // Simulate the first click
    toggleButton.click();
    fixture.detectChanges();
    expect(component.isMobileMenuOpen).toBeTrue();

    // Simulate the second click
    toggleButton.click();
    fixture.detectChanges();
    expect(component.isMobileMenuOpen).toBeFalse();
  });

  it('should apply hover styles to links (UI test)', () => {
    const linkElement = fixture.debugElement.query(By.css('a')).nativeElement;
    expect(linkElement.classList).toContain('hover:text-blue-200');
  });
});
