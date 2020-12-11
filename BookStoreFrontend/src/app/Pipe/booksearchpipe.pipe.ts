import { Pipe, PipeTransform } from '@angular/core';
import { BookModule } from '../Model/book/book.module';

@Pipe({
  name: 'booksearchpipe'
})
export class BooksearchpipePipe implements PipeTransform {

  transform(book: BookModule[], searchTerm: string) {
    if (!book || !searchTerm) {
      console.log('searching the ' + searchTerm);
      return book;
    } else {
      // tslint:disable-next-line: no-shadowed-variable
      return book.filter(book => {
            if (searchTerm && book.bookName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1) {
                return true;
            }
            if (searchTerm && book.authorName.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1) {
                return true;
            }
            if (searchTerm && book.status.toLowerCase().indexOf(searchTerm.toLowerCase()) !== -1) {
                return true;
            }
            return false;
       });

    }
  }

}
