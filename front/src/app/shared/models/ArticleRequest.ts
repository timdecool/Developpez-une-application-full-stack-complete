import {Theme} from "./Theme";
import {User} from "./User";

export interface ArticleRequest {
  title: string;
  content: string;
  themeId: number;
}
