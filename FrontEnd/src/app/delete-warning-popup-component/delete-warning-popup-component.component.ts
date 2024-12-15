import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-delete-warning-popup-component',
  templateUrl: './delete-warning-popup-component.component.html',
  styleUrls: ['./delete-warning-popup-component.component.css']
})
export class DeleteWarningPopupComponent {
  @Output() deleteConfirmed = new EventEmitter<void>();
  @Output() deleteCancelled = new EventEmitter<void>();
  @Input() unit!: string;

  onCancel() {
    this.deleteCancelled.emit();
  }

  onConfirm() {
    this.deleteConfirmed.emit();
  }
}
