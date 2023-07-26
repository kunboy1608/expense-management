import { Component } from '@angular/core';
import SharedModule from 'app/shared/shared.module';

@Component({
  standalone: true,
  selector: 'jhi-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
  imports: [SharedModule]
})
export default class FooterComponent {
  openLink(url: string) {
    window.open(url, '_blank')
  }
}
