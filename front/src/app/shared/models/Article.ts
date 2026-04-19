import {Theme} from "./Theme";
import {User} from "./User";

export interface Article {
  id: number;
  title: string;
  content: string;
  theme: Theme;
  author: User;
  date: Date;
}
