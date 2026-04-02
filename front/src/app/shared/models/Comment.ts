import {Theme} from "./Theme";
import {User} from "./User";

export interface Comment {
  id: number;
  content: string;
  author: User;
  date: Date;
}
